package com.manualidades.backend.controller;

import com.manualidades.backend.dto.OrderRequest;

import com.manualidades.backend.entity.Order;

import com.manualidades.backend.service.OrderService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {

        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {

        return orderService.getOrders();
    }

    @PostMapping
    public Order createOrder(
            @RequestBody OrderRequest request
    ) {

        return orderService.createOrder(request);
    }
}