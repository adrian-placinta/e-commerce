package com.e_commerce.inventory_service.messaging;

import events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    @KafkaListener(topics = "stock-topic", groupId = "stock-services")
    void consumeOrderEvent(OrderCreatedEvent orderCreatedEvent) {
    }
}
