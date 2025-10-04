package com.e_commerce.order_service.model.factory;

import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import events.OrderCreatedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class OrderCreatedEventFactory {

    private OrderCreatedEventFactory() {
    }

    public static OrderCreatedEvent fromOrder(Order order) {
        List<OrderCreatedEvent.OrderItemEvent> items = order.getOrderItems()
                .stream()
                .map(OrderCreatedEventFactory::toEventItem)
                .collect(Collectors.toList());

        return OrderCreatedEvent.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .orderItems(items)
                .orderStatus(order.getOrderStatus().name())
                .build();
    }

    private static OrderCreatedEvent.OrderItemEvent toEventItem(OrderItem item) {
        return OrderCreatedEvent.OrderItemEvent.builder()
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .build();
    }
}
