package com.atguigu.business.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfig {


    // 全日志打印
    @Bean
    Logger.Level feignLoggerLevel(){
        return  Logger.Level.FULL;
    };
}
