package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.orders.OrderInfo;
import com.delivery.fastfood.services.orders.OrderServie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private final OrderServie orderServie;

    public OrderResource(OrderServie orderServie) {
        this.orderServie = orderServie;
    }

    @PostMapping("/create-order")
    public ResponseEntity createOrder(@RequestBody OrderInfo orderInfo){
        return ResponseEntity.ok(orderServie.craeteOrder(orderInfo));
    }
}
