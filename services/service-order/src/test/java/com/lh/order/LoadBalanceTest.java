package com.lh.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
@Slf4j
public class LoadBalanceTest {


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    DiscoveryClient discoveryClient; //指定服务
    @Test
    public void testLoadBalance() {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        log.info("choose: {}", choose);
        System.out.println("host:"+choose.getHost()+">>"+"port"+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("host:"+choose.getHost()+">>"+"port"+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("host:"+choose.getHost()+">>"+"port"+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println("host:"+choose.getHost()+">>"+"port"+choose.getPort());
    }
}
