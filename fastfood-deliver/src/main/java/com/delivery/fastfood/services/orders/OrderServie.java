package com.delivery.fastfood.services.orders;

import com.delivery.fastfood.domain.entities.orders.Order;
import com.delivery.fastfood.domain.entities.orders.OrderInfo;
import com.delivery.fastfood.repositories.OrderItemRepository;
import com.delivery.fastfood.repositories.OrderRepository;
import com.delivery.fastfood.repositories.UserRepository;
import com.delivery.fastfood.securities.SecurityUtils;
import com.delivery.fastfood.services.users.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServie {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    public OrderServie(OrderRepository orderRepository, UserRepository userRepository, UserService userService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    public Order craeteOrder(OrderInfo orderInfo){
        String currentUserName = SecurityUtils.getUserName();
        Long currentUserId = userService.getId(currentUserName);
        Order userOrder = orderRepository.getByUserId(currentUserId);
        userOrder.setDistance(orderInfo.getDistance());
        userOrder.setYourAdress(orderInfo.getYourAdress());
        userOrder.setPhone(orderInfo.getPhone());
        userOrder.setAmountOfProducts(orderItemRepository.countByOrderId(userOrder.getId()));
        userOrder.setDeliveryTime(calculateEstimatedDeliveryTime(userOrder.getAmountOfProducts(),4));
        return userOrder;
    }

    public static LocalDateTime calculateEstimatedDeliveryTime(int numberOfItems, int distanceInKilometers) {
        int totalPreparationTime = (numberOfItems / 4) * 5 + (numberOfItems % 4 != 0 ? 5 : 0);

        // Calculate delivery time based on distance
        int deliveryTimeForDistance = distanceInKilometers * 3;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime estimatedDeliveryTime = now.plusMinutes(totalPreparationTime + deliveryTimeForDistance);

        return estimatedDeliveryTime;
    }



}
