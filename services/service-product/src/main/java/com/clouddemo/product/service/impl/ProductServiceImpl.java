package com.clouddemo.product.service.impl;

import com.clouddemo.product.bean.Product;
import com.clouddemo.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setProductName("苹果-" + productId);
        product.setPrice(new BigDecimal("99"));
        product.setNum(2);

        return product;
    }
}
