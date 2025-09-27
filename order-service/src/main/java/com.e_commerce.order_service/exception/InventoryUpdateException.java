package com.e_commerce.order_service.exception;

public class InventoryUpdateException extends RuntimeException {
    public InventoryUpdateException(String message) {
        super(message);
    }
}
