package com.imooc.product.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 减库存入参
 * Created by 廖师兄
 * 2018-01-20 22:50
 */
@Data
public class DecreaseStockInput implements Serializable {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}