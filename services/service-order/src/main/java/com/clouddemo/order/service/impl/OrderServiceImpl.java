package com.clouddemo.order.service.impl;

import com.clouddemo.order.bean.Order;
import com.clouddemo.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setId(1L);
        order.setAddress("珠海");
        order.setNickName("zhangsan");
        //TODO 总金额
        order.setTotalAmount(new BigDecimal("0"));
        //TODO 远程查询商品列表
        order.setProdctList(null);
        return order;
    }
}
