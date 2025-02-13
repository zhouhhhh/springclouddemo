package com.clouddemo.product.controller;


import com.clouddemo.product.bean.Product;
import com.clouddemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        return product;
    }
}
