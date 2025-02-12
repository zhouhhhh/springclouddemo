package com.clouddemo.product.service;

import com.clouddemo.product.bean.Product;

public interface ProductService {
    Product getProductById(Long productId);
}
