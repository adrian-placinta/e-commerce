package com.e_commerce.order_service.messaging.impl;

import events.OrderStockDeniedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class OrderEventConsumer {
    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumeOrderInventoryFailedEvent(OrderStockDeniedEvent orderStockDeniedEvent) {
        log.info(orderStockDeniedEvent.getOrderId());
    }
}
