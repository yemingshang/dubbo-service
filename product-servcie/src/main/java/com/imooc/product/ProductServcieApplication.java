package com.imooc.product;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ProductServcieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServcieApplication.class, args);
    }

}
