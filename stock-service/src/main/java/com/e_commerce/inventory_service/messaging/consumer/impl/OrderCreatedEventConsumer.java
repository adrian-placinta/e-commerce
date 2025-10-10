package com.e_commerce.inventory_service.messaging.consumer.impl;

import com.e_commerce.inventory_service.messaging.consumer.OrderEventConsumer;
import com.e_commerce.inventory_service.messaging.handler.OrderEventHandler;
import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer implements OrderEventConsumer<OrderCreatedEvent> {
    private final OrderEventHandler<OrderCreatedEvent> orderCreatedEventOrderEventHandler;

    @Override
    @KafkaListener(topics = "stock-topic", groupId = "stock-services")
    public void consume(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedEventOrderEventHandler.handle(orderCreatedEvent);
    }
}
