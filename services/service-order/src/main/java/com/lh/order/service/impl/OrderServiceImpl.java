package com.lh.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lh.order.feign.ProductFeignClient;
import com.lh.dto.order.Order;
import com.lh.dto.product.Product;
import com.lh.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public DiscoveryClient discoveryClient;

    @Autowired
    public LoadBalancerClient loadBalancerClient;

    @Qualifier("productFeignClient")
    @Autowired
    public ProductFeignClient productFeignClient;

    @Override
    @SentinelResource(value = "createOrder",blockHandler = "createOrderFallback")
    public Order createOrder(int userId, int productId) {
        Order order = new Order();
        order.setOrderId(1);
        order.setAmount(1);
        order.setCustomerId(userId);
        order.setCustomerName(String.valueOf(userId)+"号客人");
        order.setAddress("来安路688号");
        List<Product> products = new ArrayList();
        products.add(getProductByFeign(productId));
        order.setProducts(products);
        return order;
    }

    private Order createOrderFallback(int userId, int productId, BlockException e){
        Order order = new Order();
        order.setOrderId(0);
        order.setAmount(0);
        order.setCustomerId(0);
        order.setCustomerName("未知订单");
        order.setAddress(e.getMessage());
        return order;
    }


    // 固定获取服务地址
    private Product getProduct(int id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getProduct/"+id;
        log.info("固定获取服务地址>>>url>>"+url);
        return restTemplate.getForObject(url, Product.class);
    }

    // 通过负载均衡获取服务地址
    // 轮询
    private Product getProductByloadBalance(int id) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/getProduct/"+id;
        log.info("通过负载均衡获取服务地址>>>url>>"+url);
        return restTemplate.getForObject(url, Product.class);
    }

    // 通过负载均衡获取服务地址  基于注解 LoadBalanced
    // 轮询
    private Product getProductByZj(int id) {
        String url = "http://service-product/getProduct/"+id;
        log.info("通过负载均衡获取服务地址>>>url>>"+url);
        // restTemplate 开启LoadBalanced注解，会把url中的远程服务名（service-product）动态替换成远程地址
        return restTemplate.getForObject(url, Product.class);
    }

    // 通过feign调用
    private Product getProductByFeign(int id) {
        return productFeignClient.getProduct(id);
    }
}
