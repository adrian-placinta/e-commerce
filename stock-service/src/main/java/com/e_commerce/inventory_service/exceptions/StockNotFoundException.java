package com.e_commerce.inventory_service.exceptions;

import com.e_commerce.inventory_service.constant.StockExceptionMessage;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(StockExceptionMessage template, Object... args) {
        super(template.getMessage(args));
    }

}