package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.Product;
import com.delivery.fastfood.services.orders.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderItemResource {
    private final OrderItemService orderItemService;

    public OrderItemResource(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Product product){
        orderItemService.createOrderItem(product);
        return ResponseEntity.ok(product);
    }

}
