package com.delivery.fastfood.services.orders;

import com.delivery.fastfood.domain.entities.orders.Order;
import com.delivery.fastfood.domain.entities.orders.OrderInfo;
import com.delivery.fastfood.domain.enums.Status;
import com.delivery.fastfood.exception.NotFoundException;
import com.delivery.fastfood.repositories.OrderItemRepository;
import com.delivery.fastfood.repositories.OrderRepository;
import com.delivery.fastfood.repositories.UserRepository;
import com.delivery.fastfood.securities.SecurityUtils;
import com.delivery.fastfood.services.users.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServie {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;
    public OrderServie(OrderRepository orderRepository, UserRepository userRepository, UserService userService, OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    public Order craeteOrder(OrderInfo orderInfo){
        String currentUserName = SecurityUtils.getUserName();
        Long currentUserId = userService.getId(currentUserName);
        Order userOrder = orderRepository.getByUserId(currentUserId);
        userOrder.setDistance(orderInfo.getDistance());
        userOrder.setYourAdress(orderInfo.getYourAdress());
        userOrder.setPhone(orderInfo.getPhone());
        userOrder.setAmountOfProducts(orderItemService.getProductsByOrderId(userOrder.getId()));
        userOrder.setDeliveryTime(calculateEstimatedDeliveryTime(userOrder.getAmountOfProducts(),4));
        return userOrder;
    }

    public static String calculateEstimatedDeliveryTime(int numberOfItems, int distanceInKilometers) {
        int totalPreparationTime = (numberOfItems / 4) * 5 + (numberOfItems % 4 != 0 ? 5 : 0);

        int deliveryTimeForDistance = distanceInKilometers * 3;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime estimatedDeliveryTime = now.plusMinutes(totalPreparationTime + deliveryTimeForDistance);
        String devTime = String.valueOf(estimatedDeliveryTime.getHour()+ estimatedDeliveryTime.getMinute()+"minutes");
        return devTime;
    }

    public void acceptOrder(Long orderId){
        Order order = orderRepository.getById(orderId);
        if(order == null){
            throw new NotFoundException("Order not found!");
        }
        if(order.getCart() == true) {
            order.setStatus(Status.ACCEPTED);
        }
    }

    public void rejectOrder(Long orderId){
        Order order = orderRepository.getById(orderId);
        if(order == null){
            throw new NotFoundException("Order not found!");
        }
        if(order.getCart() == true) {
            order.setStatus(Status.REJECTED);
            order.setCart(false);
            Order newOrder = new Order();
            newOrder.setUser(order.getUser());
        }
    }

    public void deliverOrder(Long orderId){
        Order order = orderRepository.getById(orderId);
        if(order == null){
            throw new NotFoundException("Order not found!");
        }
        if(order.getCart() == true) {
            order.setStatus(Status.DELIVERED);
            order.setCart(false);
            Order newOrder = new Order();
            newOrder.setUser(order.getUser());
        }
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public List<Order> getAllOrder



}
