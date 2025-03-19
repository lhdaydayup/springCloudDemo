package com.lh.order;

import com.lh.order.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;

@SpringBootTest
public class WeatherApiTest {

    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    public void testThirdApi() {
        //调用第3方api
        String weatherinfo = weatherFeignClient.getWeather("APPCODE 93b7e19861a24c519a7548b17dc1675","50b53ff8dd7d9fa320d3d3ca32cf8ed1","2182");
        System.out.println("天气信息："+weatherinfo);
    }
}
