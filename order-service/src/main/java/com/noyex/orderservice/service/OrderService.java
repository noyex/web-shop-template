package com.noyex.orderservice.service;

import com.noyex.orderservice.entity.Order;
import com.noyex.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    public final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Order createOrder(Order order) {
        String userUrl = "http://localhost:8082/users/" + order.getUserId();
        if(!restTemplate.getForEntity(userUrl, Object.class).getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("User not found");
        }

        String productUrl = "http://localhost:8081/products/" + order.getProductId();
        if(!restTemplate.getForEntity(productUrl, Object.class).getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Product not found");
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
