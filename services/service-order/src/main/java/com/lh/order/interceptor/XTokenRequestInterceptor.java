package com.lh.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component //放到容器中  spring feign 会自己找到对应拦截器
public class XTokenRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("XTokenRequestInterceptor");
        requestTemplate.header("x_Token", UUID.randomUUID().toString());
    }
}
