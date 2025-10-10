package com.e_commerce.order_service.messaging.producer;

public interface OrderEventProducer<T> {
    void produce(T event);
}
