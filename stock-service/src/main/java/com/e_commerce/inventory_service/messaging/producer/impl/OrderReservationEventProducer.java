package com.e_commerce.inventory_service.messaging.producer.impl;

import com.e_commerce.inventory_service.messaging.producer.OrderEventProducer;
import events.OrderStockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReservationEventProducer implements OrderEventProducer<OrderStockReservedEvent> {
    private final KafkaTemplate<String, OrderStockReservedEvent> kafkaTemplate;

    @Override
    public void produce(OrderStockReservedEvent orderStockReservedEvent) {
        kafkaTemplate.send("order-topic", orderStockReservedEvent);
    }
}
