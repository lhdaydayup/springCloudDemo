package com.lh.order.interceptor;

import feign.InvocationContext;
import feign.ResponseInterceptor;

//@Component //放到容器中  spring feign 会自己找到对应拦截器
public class TestResponseIntercetor implements ResponseInterceptor {
    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        System.out.println("intercept>>>>>"+invocationContext.response().body().toString());
        return null;
    }

    @Override
    public Chain apply(Chain chain) {
        System.out.println("chain>>>>>>>"+chain.toString());
        return ResponseInterceptor.super.apply(chain);
    }


}
