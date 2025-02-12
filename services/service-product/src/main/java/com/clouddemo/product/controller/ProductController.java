package com.clouddemo.product.controller;


import com.clouddemo.product.bean.Product;
import com.clouddemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    public Product getProduct(Long productId) {
        Product product = productService.getProductById(productId);
        return product;
    }
}
