package com.lh.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class OnceTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<OnceTokenGatewayFilterFactory.Config> {

    public OnceTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {

            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //每次响应之前，添加一个一次性令牌，支持uuid,jwt等格式
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    log.info("添加一次性令牌A");
                    ServerHttpResponse response = exchange.getResponse();
                    HttpHeaders headers = response.getHeaders();
                    String value = config.getValue();
                    if("uuid".equalsIgnoreCase(value)){
                        value = UUID.randomUUID().toString();
                    }

                    if("jwt".equalsIgnoreCase(value)){
                        value = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjQ5NjYwMDAwLCJleHAiOjE2NDk2NjM2MDB9.1Q6z8z8z8z8z8z8z8z8z8z8z8z8z8z8z8z8z8";
                    }
                    log.info("添加一次性令牌B：{}", value);
                    headers.add(config.getName(), value);
                }));
            }
        };
    }

    @Data
    public static class Config {

        private String name;
        private String value;

    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "value");
    }
}