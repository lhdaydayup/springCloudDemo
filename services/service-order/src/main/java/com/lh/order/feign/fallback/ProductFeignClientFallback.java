package com.lh.order.feign.fallback;

import com.lh.order.feign.ProductFeignClient;
import com.lh.dto.product.Product;
import org.springframework.stereotype.Component;

@Component // 放到容器中去
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProduct(int id) {
        System.out.println("兜底回调");
        Product product = new Product();
        product.setId(id);
        product.setProductName("默认");
        product.setPrice(0);
        product.setNum(0);
        return product;
    }
}
