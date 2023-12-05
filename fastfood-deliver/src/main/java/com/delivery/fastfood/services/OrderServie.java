package com.delivery.fastfood.services;

import com.delivery.fastfood.domain.entities.Order;
import com.delivery.fastfood.domain.entities.Product;
import com.delivery.fastfood.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServie {
    private final OrderRepository orderRepository;

    public OrderServie(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public Order createOrder(List<Product> products){
//
//    }
}
