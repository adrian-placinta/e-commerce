package com.e_commerce.product_service.controller;

import com.e_commerce.product_service.dto.NotFoundExceptionRes;
import com.e_commerce.product_service.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundExceptionRes productNotFoundHandler(ProductNotFoundException productNotFoundException, HttpServletRequest httpServletRequest) {
        return new NotFoundExceptionRes(productNotFoundException.getMessage(),
                Instant.now().toString(),
                HttpStatus.NOT_FOUND,
                httpServletRequest.getServletPath()
        );
    }
}
