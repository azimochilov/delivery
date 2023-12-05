package com.delivery.fastfood.services;

import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.domain.entities.Order;
import com.delivery.fastfood.domain.entities.OrderItem;
import com.delivery.fastfood.domain.entities.Product;
import com.delivery.fastfood.repositories.MenuRepository;
import com.delivery.fastfood.repositories.OrderItemRepository;
import com.delivery.fastfood.repositories.OrderRepository;
import com.delivery.fastfood.securities.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderItemService(OrderItemRepository orderItemRepository, MenuRepository menuRepository, OrderRepository orderRepository, UserService userService) {
        this.orderItemRepository = orderItemRepository;
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public OrderItem createOrderItem(Product product){
        String currentUser = SecurityUtils.getUserName();
        Long currentId = userService.getId(currentUser);
        Order userOrder = orderRepository.getByUserId(currentId);

        Menu menu = menuRepository.findByName(product.getName());
        if(menu == null){
            throw new RuntimeException("Product for given name not found!");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(userOrder);
        orderItem.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        orderItem.setCount(product.getCount());
        orderItem.setName(product.getName());
        orderItem.setMenu(menu);
        return orderItemRepository.save(orderItem);
    }


}
