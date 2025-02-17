package com.clouddemo.order.controller;


import com.clouddemo.order.bean.Order;
import com.clouddemo.order.properties.OrderProperties;
import com.clouddemo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope //自动刷新功能
@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;
//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;
    /**
     * 创建订单
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,
                             @RequestParam("userId") Long userId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    @GetMapping("/config")
    public String config() {
//        return "order timeout: " + orderTimeout + " auto-confirm: " + orderAutoConfirm;
        return "order timeout: " + orderProperties.getTimeout() +
                " auto-confirm: " + orderProperties.getAutoConfirm();
    }
}

