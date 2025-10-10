package com.e_commerce.order_service.messaging.producer.impl;

import com.e_commerce.order_service.messaging.producer.OrderEventProducer;
import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("orderCreatedEventProducer")
@RequiredArgsConstructor
public class OrderCreatedEventProducer implements OrderEventProducer<OrderCreatedEvent> {
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Override
    public void produce(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send("stock-topic", orderCreatedEvent);
    }
}
