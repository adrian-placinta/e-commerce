package com.e_commerce.order_service.repository;

import com.e_commerce.order_service.model.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllOrdersByUserId(final String userId, PageRequest pageRequest);
}
