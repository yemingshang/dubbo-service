package com.imooc.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by zjs
 */
@Service(version = "${demo.service.version}")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

   /* @Autowired
    private AmqpTemplate amqpTemplate;*/

    @Override
    public List<ProductInfoOutput> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode()).stream()
                .map(e-> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {

        /*for (int i = 0; i < 6000; i++) {
            for (int j = 0; j < 1000; j++) {
                String str  = new String();
                str = String.valueOf(j);
            }
        }*/

        for (int i = 0; i < 19; i++) {
            productInfoRepository.findByProductIdIn(productIdList);
        }
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
       for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
           Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
           //判断商品是否存在
           if (!productInfoOptional.isPresent()){
               throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
           }

           ProductInfo productInfo = productInfoOptional.get();
           //库存是否足够
           Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
           if (result < 0) {
               throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
           }
           productInfo.setProductStock(result);
           productInfoRepository.save(productInfo);
       }
       return true;
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
