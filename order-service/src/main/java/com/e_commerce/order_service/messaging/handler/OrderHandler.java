package com.e_commerce.order_service.messaging.handler;

public interface OrderHandler<T> {
    void handle(T event);
}
