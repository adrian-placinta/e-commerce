package com.e_commerce.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic orderTopic() {
        return new NewTopic("order-topic", 3, (short) 1);
    }

    @Bean
    NewTopic inventoryTopic() {
        return new NewTopic("inventory-topic", 3, (short) 1);
    }

    @Bean
    NewTopic paymentTopic() {
        return new NewTopic("order-topic", 3, (short) 1);
    }
}
