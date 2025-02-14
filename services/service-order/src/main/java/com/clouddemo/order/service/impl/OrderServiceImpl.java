package com.clouddemo.order.service.impl;

import com.clouddemo.order.bean.Order;
import com.clouddemo.order.service.OrderService;
import com.clouddemo.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
        //Product product = getProductFromRemote(productId);
        Product product = getProductFromRemoteLoadBalanced(productId);
        Order order = new Order();
        order.setUserId(userId);
        order.setId(1L);
        order.setAddress("珠海");
        order.setNickName("zhangsan");
        //总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        //TODO 远程查询商品列表
        order.setProdctList(Arrays.asList(product));
        return order;
    }

    private Product getProductFromRemote(Long productId) {
        //1.获取到商品服务所在的所有机器IP
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        //远程url
        String url =  "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("url:" + url);
        //2.给远程服务发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * 负载均衡调用
     * @param productId
     * @return
     */
    private Product getProductFromRemoteLoadBalancer(Long productId) {
        //1.获取到商品服务所在的所有机器IP
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //远程url
        String url =  "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("url:" + url);
        //2.给远程服务发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * 负载均衡注解
     */
    private Product getProductFromRemoteLoadBalanced(Long productId) {
        return restTemplate.getForObject(
                "http://service-product/product/" + productId,
                Product.class);
    }
}
