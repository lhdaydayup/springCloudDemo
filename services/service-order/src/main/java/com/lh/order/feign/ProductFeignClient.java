package com.lh.order.feign;

import com.lh.order.feign.fallback.ProductFeignClientFallback;
import com.lh.dto.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//调用服务名
@FeignClient(name="service-product",value="service-product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    //mvc注解的两套使用逻辑
    //1.标志在Controller上是接受这样的请求
    //2.标注在FeignClient上是发送这样的请求
    //@GetMapping("/api/product/getProduct/{id}")  gateway重写路径
    @GetMapping("/getProduct/{id}")
    Product getProduct(@PathVariable("id")int id);
}
