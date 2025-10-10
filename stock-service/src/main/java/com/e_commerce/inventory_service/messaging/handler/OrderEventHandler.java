package com.e_commerce.inventory_service.messaging.handler;

public interface OrderEventHandler<T> {
    void handle(final T event);
}
