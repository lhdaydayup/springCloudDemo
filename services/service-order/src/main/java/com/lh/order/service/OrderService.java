package com.lh.order.service;


import com.lh.dto.order.Order;

public interface OrderService {
    Order createOrder(int userId, int productId);
}
