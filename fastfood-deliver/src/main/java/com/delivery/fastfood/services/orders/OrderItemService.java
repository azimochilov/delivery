package com.delivery.fastfood.services.orders;


import com.delivery.fastfood.domain.entities.Menu;
import com.delivery.fastfood.domain.entities.Product;
import com.delivery.fastfood.domain.entities.orders.Order;
import com.delivery.fastfood.domain.entities.orders.OrderItem;
import com.delivery.fastfood.repositories.MenuRepository;
import com.delivery.fastfood.repositories.OrderItemRepository;
import com.delivery.fastfood.repositories.OrderRepository;
import com.delivery.fastfood.securities.SecurityUtils;
import com.delivery.fastfood.services.users.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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
        Double total = (menu.getPrice()* product.getCount());
        if(userOrder.getTotalPrice() == null){
            userOrder.setTotalPrice(total);
            userOrder.setAmountOfProducts(product.getCount());
        }else {
            userOrder.setTotalPrice(userOrder.getTotalPrice() + total);
            userOrder.setAmountOfProducts(userOrder.getAmountOfProducts()+product.getCount());
        }
        orderRepository.save(userOrder);
        return orderItemRepository.save(orderItem);
    }

    public Integer getProductsByOrderId(Long orderId){
        List<OrderItem> relatedItems = new ArrayList<>();

        for(OrderItem orderItem : orderItemRepository.findAll()){
            if(orderItem.getOrder().getId() == orderId){
                relatedItems.add(orderItem);
            }
        }

        return getCountOfProducts(relatedItems);
    }

    public Integer getCountOfProducts(List<OrderItem> orderItems){
        Integer count = 0;
        for(OrderItem orderItem : orderItems){
            count += orderItem.getCount();
        }
        return count;
    }


}
