package com.lh.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

    //重试机制配置对象
    @Bean
    Retryer retryer(){
        return new Retryer.Default();
    };

    // 全日志打印
    @Bean
    Logger.Level feignLoggerLevel(){
        return  Logger.Level.FULL;
    };

    @Bean
    // restTemplate 开启LoadBalanced注解，会把url中的远程服务名（service-product）动态替换成远程地址
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
