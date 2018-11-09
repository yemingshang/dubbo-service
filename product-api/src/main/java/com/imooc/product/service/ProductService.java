package com.imooc.product.service;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;

import java.util.List;

/**
 * Created by zjs
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfoOutput> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    Boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
