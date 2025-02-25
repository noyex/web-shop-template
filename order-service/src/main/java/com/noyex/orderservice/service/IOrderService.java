package com.noyex.orderservice.service;

import com.noyex.orderservice.entity.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(Order order);
    List<Order> getOrdersByUserId(Long userId);
}
