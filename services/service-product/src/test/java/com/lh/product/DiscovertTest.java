package com.lh.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscovertTest {

    @Autowired
    DiscoveryClient discoveryClient;

    // @Autowired
    // NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void discoverClientTest(){
        for (String serviceId : discoveryClient.getServices()) {
            System.out.println("service>>>"+serviceId);

            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instance : instances) {
                System.out.println("instance-id>>>"+instance.getInstanceId());
                System.out.println("instance-host>>>"+instance.getHost());
            }
        }
    };
}
