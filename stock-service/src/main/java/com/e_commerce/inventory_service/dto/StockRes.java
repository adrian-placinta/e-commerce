package com.e_commerce.inventory_service.dto;

public record StockRes(
        long stockId,
        long productId,
        long quantity
) {
}