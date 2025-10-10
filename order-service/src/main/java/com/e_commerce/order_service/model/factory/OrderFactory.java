package com.e_commerce.order_service.model.factory;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import com.e_commerce.order_service.util.OrderMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderFactory {

    private OrderFactory() {
    }

    public static Order createOrder(String userId, OrderReq orderReq) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderItems(setOrderItems(orderReq, order));

        return order;
    }

    private static List<OrderItem> setOrderItems(OrderReq orderReq, Order order) {
        return Optional.ofNullable(orderReq.getOrderItems())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(OrderMapper::toOrderItem)
                .peek(orderItem -> orderItem.setOrder(order))
                .toList();
    }
}
