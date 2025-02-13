package com.clouddemo.order.service;

import com.clouddemo.order.bean.Order;

public interface OrderService {

    Order createOrder(Long productId, Long userId);
}
