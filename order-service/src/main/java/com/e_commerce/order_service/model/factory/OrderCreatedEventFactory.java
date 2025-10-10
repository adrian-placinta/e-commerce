package com.e_commerce.order_service.model.factory;

import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import events.OrderCreatedEvent;

import java.time.Instant;
import java.util.List;

public class OrderCreatedEventFactory {

    private OrderCreatedEventFactory() {
    }

    public static OrderCreatedEvent fromOrder(Order order) {
        List<OrderCreatedEvent.OrderItem> items = order.getOrderItems()
                .stream()
                .map(OrderCreatedEventFactory::toEventItem)
                .toList();

        return new OrderCreatedEvent(
                order.getOrderId(),
                items,
                Instant.now()
        );
    }

    private static OrderCreatedEvent.OrderItem toEventItem(OrderItem item) {
        return new OrderCreatedEvent.OrderItem(
                item.getProductId(),
                item.getQuantity()
        );
    }
}