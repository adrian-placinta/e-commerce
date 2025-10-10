package com.e_commerce.inventory_service.exceptions;

import com.e_commerce.inventory_service.constant.StockExceptionMessage;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(StockExceptionMessage template, Object... args) {
        super(template.getMessage(args));
    }

}