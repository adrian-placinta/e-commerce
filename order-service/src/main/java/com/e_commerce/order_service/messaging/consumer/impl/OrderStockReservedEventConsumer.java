package com.e_commerce.order_service.messaging.consumer.impl;

import com.e_commerce.order_service.messaging.consumer.OrderEventConsumer;
import com.e_commerce.order_service.messaging.handler.OrderHandler;
import events.OrderStockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component("orderStockReservedEventConsumer")
@RequiredArgsConstructor
public class OrderStockReservedEventConsumer implements OrderEventConsumer<OrderStockReservedEvent> {
    private final OrderHandler<OrderStockReservedEvent> orderStockReservedEventOrderHandler;

    @Override
    @KafkaListener(topics = "order-topic", groupId = "order-services")
    public void consume(OrderStockReservedEvent orderStockReservedEvent) {
        orderStockReservedEventOrderHandler.handle(orderStockReservedEvent);
    }
}
