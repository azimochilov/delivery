package com.delivery.fastfood.services;

import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.domain.entities.OrderItem;
import com.delivery.fastfood.repositories.MenuRepository;
import com.delivery.fastfood.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, MenuRepository menuRepository) {
        this.orderItemRepository = orderItemRepository;
        this.menuRepository = menuRepository;
    }

}
