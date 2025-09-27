package com.e_commerce.order_service.repository;

import com.e_commerce.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(String userId);

    @Query(value = """
            SELECT o1.*
            FROM orders AS o1
            JOIN `orders-items` AS oi
              ON o1.order_id = oi.order_id
            WHERE o1.user_id = :userId
              AND o1.order_id = :orderId
            """, nativeQuery = true)
    Optional<Order> findOrderByUserIdAndOrderId(String userId, Long orderId);
}
