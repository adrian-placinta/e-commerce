package com.e_commerce.order_service.messaging.consumer;

public interface OrderEventConsumer<T> {
    void consume(T event);
}
