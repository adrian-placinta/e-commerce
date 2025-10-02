package com.e_commerce.order_service.model.factory;

import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import com.e_commerce.order_service.model.OrderStatus;

import java.util.List;

public class OrderFactory {

    private OrderFactory() {
    }

    public static Order createOrder(String userId, List<OrderItem> orderItems, OrderStatus orderStatus) {
        return Order.builder()
                .orderStatus(orderStatus)
                .userId(userId)
                .orderItems(orderItems)
                .build();
    }
}
