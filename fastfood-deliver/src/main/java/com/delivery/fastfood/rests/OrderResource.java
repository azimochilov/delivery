package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.orders.OrderInfo;
import com.delivery.fastfood.services.orders.OrderServie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private final OrderServie orderServie;

    public OrderResource(OrderServie orderServie) {
        this.orderServie = orderServie;
    }

    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestBody OrderInfo orderInfo){
        return ResponseEntity.ok(orderServie.craeteOrder(orderInfo));
    }

    @PatchMapping("/order/status/reject")
    public ResponseEntity changeStatusToReject(@RequestBody Long orderId){
        orderServie.rejectOrder(orderId);
        return ResponseEntity.ok("Order with given id Rejected!! ");
    }

    @PatchMapping("/order/status/accept")
    public ResponseEntity changeStatusToAccept(@RequestBody Long orderId){
        orderServie.acceptOrder(orderId);
        return ResponseEntity.ok("Order with given id Accepted!! ");
    }

    @PatchMapping("/order/status/deliver")
    public ResponseEntity changeStatusToDeliver(@RequestBody Long orderId){
        orderServie.deliverOrder(orderId);
        return ResponseEntity.ok("Order with given id Delivered!! ");
    }


}
