package com.manualidades.backend.service;

import com.manualidades.backend.entity.Order;

import com.manualidades.backend.entity.Product;

import com.manualidades.backend.entity.OrderItem;

import com.manualidades.backend.dto.OrderRequest;

import com.manualidades.backend.dto.OrderItemRequest;

import com.manualidades.backend.repository.OrderRepository;

import com.manualidades.backend.repository.ProductRepository;

import com.manualidades.backend.repository.OrderItemRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {

        this.orderRepository = orderRepository;

        this.productRepository = productRepository;

        this.orderItemRepository =
                orderItemRepository;
    }

    public List<Order> getOrders() {

        return orderRepository.findAll();
    }

    public Order createOrder(
            OrderRequest request
    ) {

        Order order = new Order();

        double total = 0;

        List<OrderItem> items =
                new ArrayList<>();

        for (OrderItemRequest itemRequest
                : request.getItems()) {

            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow();

            OrderItem item = new OrderItem();

            item.setOrder(order);

            item.setProduct(product);

            item.setQuantity(
                    itemRequest.getQuantity()
            );

            item.setPrice(
                    product.getPrice()
            );

            total +=
                    product.getPrice()
                            * itemRequest.getQuantity();

            items.add(item);
        }

        order.setItems(items);

        order.setTotal(total);

        return orderRepository.save(order);
    }
}