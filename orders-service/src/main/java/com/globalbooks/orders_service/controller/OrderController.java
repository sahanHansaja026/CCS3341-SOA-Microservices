package com.globalbooks.orders_service.controller;

import com.globalbooks.orders_service.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private Map<String, Order> orders = new HashMap<>();

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        String id = UUID.randomUUID().toString();
        order.setId(id);
        order.setTotal(order.getQuantity() * 50); // example price
        orders.put(id, order);
        return order;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orders.get(id);
    }
}