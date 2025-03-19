package com.lh.product.controller;

import com.lh.dto.product.Product;
import com.lh.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// @RequestMapping ("/api/product") gateway通过过滤器重写路径
public class ProductController {

    @Autowired
    ProductService productServcie;

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable("id") int id, HttpServletRequest request) {
        log.info("hello>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>x_token>>>{}", request.getHeader("x_token"));
        return productServcie.getProduct(id);
    }

}
