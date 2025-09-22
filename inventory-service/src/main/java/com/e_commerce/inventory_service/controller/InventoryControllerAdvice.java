package com.e_commerce.inventory_service.controller;

import com.e_commerce.inventory_service.dto.StockNotFoundExceptionRes;
import com.e_commerce.inventory_service.exception.StockNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class InventoryControllerAdvice {
    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    StockNotFoundExceptionRes stockNotFoundHandler(StockNotFoundException stockNotFoundException,
                                                   HttpServletRequest httpRequest) {
        return new StockNotFoundExceptionRes(stockNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                Instant.now(),
                httpRequest.getServletPath());
    }
}
