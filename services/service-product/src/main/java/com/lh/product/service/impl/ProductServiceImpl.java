package com.lh.product.service.impl;

import com.lh.dto.product.Product;
import com.lh.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(int id) {
            Product product = new Product();
            product.setId(id);
            product.setProductName("name"+id);
            product.setPrice(10);
            product.setNum(10);
      /*  try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        /*try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return product;
    }
}
