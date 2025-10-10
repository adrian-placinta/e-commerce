package com.e_commerce.inventory_service.messaging.producer.impl;

import com.e_commerce.inventory_service.messaging.producer.OrderEventProducer;
import events.OrderStockDeniedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("orderStockDeniedEventProducer")
@RequiredArgsConstructor
public class OrderStockDeniedEventProducer implements OrderEventProducer<OrderStockDeniedEvent> {
    private final KafkaTemplate<String, OrderStockDeniedEvent> kafkaTemplate;

    @Override
    public void produce(OrderStockDeniedEvent orderStockDeniedEvent) {
        this.kafkaTemplate.send("order-topic", orderStockDeniedEvent);
    }
}
