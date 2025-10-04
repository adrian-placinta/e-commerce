package com.e_commerce.order_service.messaging;

import events.OrderInventoryFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class OrderEventConsumer {
    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumeOrderInventoryFailedEvent(OrderInventoryFailedEvent orderInventoryFailedEvent) {
        log.info(orderInventoryFailedEvent.getOrderId());
    }
}
