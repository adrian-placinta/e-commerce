package com.e_commerce.order_service.messaging.consumer.impl;

import com.e_commerce.order_service.messaging.consumer.OrderEventConsumer;
import com.e_commerce.order_service.messaging.handler.OrderHandler;
import events.OrderStockDeniedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component("orderStockDeniedEventConsumer")
@RequiredArgsConstructor
public class OrderStockDeniedEventConsumer implements OrderEventConsumer<OrderStockDeniedEvent> {
    private final OrderHandler<OrderStockDeniedEvent> orderStockDeniedEventOrderHandler;

    @Override
    @KafkaListener(topics = "order-topic", groupId = "order-services")
    public void consume(OrderStockDeniedEvent orderStockDeniedEvent) {
        orderStockDeniedEventOrderHandler.handle(orderStockDeniedEvent);
    }
}
