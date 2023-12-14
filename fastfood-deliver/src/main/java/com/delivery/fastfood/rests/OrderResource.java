package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.orders.OrderInfo;
import com.delivery.fastfood.services.orders.OrderServie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    private final OrderServie orderServie;

    public OrderResource(OrderServie orderServie) {
        this.orderServie = orderServie;
    }

    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody OrderInfo orderInfo) {
        return ResponseEntity.ok(orderServie.craeteOrder(orderInfo));
    }

    @PatchMapping("/status/reject")
    public ResponseEntity changeStatusToReject(@RequestBody Long orderId) {
        orderServie.rejectOrder(orderId);
        return ResponseEntity.ok("Order with given id Rejected!! ");
    }

    @PatchMapping("/status/accept")
    public ResponseEntity changeStatusToAccept(@RequestBody Long orderId) {
        orderServie.acceptOrder(orderId);
        return ResponseEntity.ok("Order with given id Accepted!! ");
    }

    @PatchMapping("/status/deliver/{id}")
    public ResponseEntity changeStatusToDeliver(@PathVariable("id") Long orderId) {
        orderServie.deliverOrder(orderId);
        return ResponseEntity.ok("Order with given id Delivered!! ");
    }

//    @GetMapping
//    public ResponseEntity getAll() {
//        return ResponseEntity.ok(orderServie.getAllOrder());
//    }

    @GetMapping("/false")
    public ResponseEntity getAllIsCartFalse() {
        return ResponseEntity.ok(orderServie.getAllOrderIsCartFalse());
    }

    @GetMapping("/true")
    public ResponseEntity getAllIsCartTrue() {
        return ResponseEntity.ok(orderServie.getAllOrderIsCartTrue());
    }

    @GetMapping
    public ResponseEntity<?> getAllByStatus(@RequestParam(name = "status", required = false) Boolean isActive) {
        if (isActive == null) {
            return ResponseEntity.ok(orderServie.getAllOrder());
        }
        return ResponseEntity.ok(orderServie.getAllByStatus(isActive));
    }
}
