package com.e_commerce.order_service.model;

public enum OrderStatus {
    PENDING,
    INVENTORY_RESERVED,
    PAYMENT_COMPLETED,
    COMPLETED,
    INVENTORY_FAILED,
    PAYMENT_FAILED,
    CANCELED
}
