package com.lh.dto.order;

import com.lh.dto.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class Order {
    private int orderId;//订单id
    private int amount;//订单金额
    private int customerId;//消费者id
    private String customerName;
    private String address;
    private List<Product> products;

}
