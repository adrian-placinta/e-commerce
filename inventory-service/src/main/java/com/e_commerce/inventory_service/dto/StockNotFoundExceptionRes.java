package com.e_commerce.inventory_service.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record StockNotFoundExceptionRes(String message, HttpStatus httpStatus, Instant date, String path) {
}
