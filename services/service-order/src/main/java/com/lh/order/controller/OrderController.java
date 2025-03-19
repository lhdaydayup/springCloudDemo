package com.lh.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lh.dto.order.Order;
import com.lh.dto.product.Product;
import com.lh.order.properties.OrderProperties;
import com.lh.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
/*@RefreshScope*/ //激活配置属性自动刷新功能
//@RequestMapping("/api/order") gateway重写路径
public class OrderController {

    /*@Value("${order.day}")
    public String orderDay;
    @Value("${order.timeout}")
    public String timeout;*/
    @Autowired
    public OrderService orderService;

    @Autowired
    public OrderProperties orderProperties;

    @GetMapping("/order")
    Order createOrder(@RequestParam("userId") int userId, @RequestParam("productId") int productId) {
        return orderService.createOrder(userId,productId);
    }


    @GetMapping("/secKillorder") //秒杀
    //@SentinelResource(value = "sck-order",blockHandler = "secKillBlockHandler") //blockHandlder只能针对BlockException异常
    @SentinelResource(value = "sck-order",fallback = "secKillFallback") //fallback能针对于所有异常 Throwable
    public Order secKillorder(@RequestParam(value = "userId",required = false) Integer userId, @RequestParam(value = "productId",required = false) Integer productId) {
        userId = 10000;
        return orderService.createOrder(userId,productId);
    }

    public Order secKillBlockHandler(Integer userId,Integer productId, BlockException e) {
        Order order = new Order();
        order.setOrderId(999);
        order.setAmount(99);
        order.setCustomerId(99);
        order.setCustomerName("99");
        order.setAddress(e.getMessage());
        order.setProducts(null);
        return order;
    }

    public Order secKillFallback(Integer userId,Integer productId, Throwable e) {
        Order order = new Order();
        order.setOrderId(999);
        order.setAmount(99);
        order.setCustomerId(99);
        order.setCustomerName("99");
        order.setAddress(e.getMessage());
        order.setProducts(null);
        return order;
    }



    @GetMapping("/getConfig")
    String getConfig() {
        //return orderDay+timeout;
        return orderProperties.toString();
       // return orderProperties.getDay()+orderProperties.getTimeout()+orderProperties.getUrl();
    }

    // 关联模式 另一个访问量过大是，该流量限制生效
    @GetMapping("/readDb")
    String readDb() {
        log.info("readDb is success .................");
        return "readDb成功";
    }


    @GetMapping("/writeDb")
    String writeDb() {
        log.info("writeDb is success .................");
        return "writeDb成功";
    }





}
