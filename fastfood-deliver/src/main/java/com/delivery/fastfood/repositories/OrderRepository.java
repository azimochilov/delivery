package com.delivery.fastfood.repositories;

import com.delivery.fastfood.domain.entities.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order getByUserId (Long userId);
    Order getById(Long orderId);
    List<Order> findAllByIsCart(Boolean status);
}
