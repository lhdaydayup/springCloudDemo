package com.lh.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RtGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis(); // 记录请求时间
        String requestUrl = exchange.getRequest().getURI().toString(); // 获取请求URL

        Mono<Void> fileter = chain.filter(exchange).doFinally(result -> {
             long endTime = System.currentTimeMillis(); // 记录响应时间
             long duration = endTime - startTime; // 计算耗时
             log.info("Request URL: {}, Processing time: {} ms", requestUrl, duration); // 记录请求URL和耗时
         });
        return fileter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
