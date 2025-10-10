package com.e_commerce.inventory_service.messaging.producer;

public interface OrderEventProducer<T> {
    void produce(T object);
}
