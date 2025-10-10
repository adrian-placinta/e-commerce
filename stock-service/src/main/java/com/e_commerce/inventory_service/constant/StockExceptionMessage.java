package com.e_commerce.inventory_service.constant;

public enum StockExceptionMessage {
    NOT_FOUND("Stock cannot be found for product with id %s"),
    INSUFFICIENT("Insufficient stock for productId: %s (available: %d, requested: %d)");

    private final String message;

    StockExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}