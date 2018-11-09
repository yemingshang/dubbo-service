package com.imooc.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author zjs
 * @date 18-10-30 上午11:12
 */

@RestController
public class TestController {
    @Reference(version = "${demo.service.version}")
    private ProductService productService;

    @GetMapping("/productDescreasestock")
    public void productDescreasestock() {
        productService.decreaseStock(Arrays.asList(new DecreaseStockInput("15771",2)));
    }

    @PostMapping("/getProductList")
    public List<ProductInfoOutput>  getProductList(@RequestBody List<String> list) {

        return productService.findList(list);
    }

    @PostMapping("/test")
    public List<ProductInfoOutput> test2RPC(@RequestBody List<String> list) {
        List<ProductInfoOutput> product = null;
        for (int i = 0; i < 100; i++) {
            product = productService.findList(list);
        }
        return product;
    }
}
