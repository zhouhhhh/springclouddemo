package com.clouddemo.order;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class NacosDiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println("service: " + service);
            for (ServiceInstance instance : nacosServiceDiscovery.getInstances(service)) {
                System.out.println("ip:" + instance.getHost() + "port:" + instance.getPort());
            }
        }
    }


    @Test
    void discoveryClientTest() {
        for (String serviceId : discoveryClient.getServices()) {
            System.out.println("service = " + serviceId);
            //获取ip和端口
            for (ServiceInstance instance : discoveryClient.getInstances(serviceId)) {
                System.out.println("ip: " + instance.getHost());
                System.out.println("port: " + instance.getPort());
            }
        }
    }
}
