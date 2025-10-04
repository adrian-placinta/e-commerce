package com.e_commerce.order_service.messaging;

import com.e_commerce.order_service.messaging.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreatedEvent(final OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send("stock-topic", orderCreatedEvent);
    }

}
