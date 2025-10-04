package com.e_commerce.inventory_service.messaging;

import events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {
    @KafkaListener(topics = "stock-topic", groupId = "stock-services")
    void consumeOrderEvent(OrderCreatedEvent orderCreatedEvent) {
        log.info(orderCreatedEvent.getOrderStatus());
    }
}
