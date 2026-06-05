package com.manualidades.backend.repository;

import com.manualidades.backend.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}