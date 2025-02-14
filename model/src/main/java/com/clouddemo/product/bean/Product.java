package com.clouddemo.product.bean;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Product {
    private Long Id;
    private BigDecimal price;
    private String productName;
    private int num;
}
