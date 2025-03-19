package com.lh.predicates;


import jakarta.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {

    public VipRoutePredicateFactory() {
        super(VipRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.getParam());
                log.info(">>>>>>>>>>>>>>>>>>>>{}", first);
                return StringUtils.hasText(first) && first.equals(config.getValue());
            }
        };
    }


    /* 可以配置的参数

     */
    @Validated
    public static class Config {
        @NotEmpty
        private String param;
        @NotEmpty
        private String value;

        public @NotEmpty String getParam() {
            return param;
        }
        public @NotEmpty String getValue() {
            return value;
        }
        public void setParam(@NotEmpty String param) {
            this.param = param;
        }
        public void setValue(@NotEmpty String value) {
            this.value = value;
        }
    }
}
