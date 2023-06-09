package com.example.orderservice.controller;

import com.example.orderservice.models.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException {
        Order order = orderService.placeAnOrder(orderRequest);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable String orderId) {
        Order order = orderService.cancellAnOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Order>> getOrdersForEvent(@PathVariable String eventId) {
        List<Order> orders = orderService.getAllOrdersForEvent(eventId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable long userId) {
        List<Order> orders = orderService.getAllOrdersForUser(userId);
        return ResponseEntity.ok(orders);
    }
}
