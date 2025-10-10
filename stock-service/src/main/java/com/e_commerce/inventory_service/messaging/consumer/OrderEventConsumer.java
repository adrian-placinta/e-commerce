package com.e_commerce.inventory_service.messaging.consumer;

public interface OrderEventConsumer<T> {
    void consume(T object);
}
