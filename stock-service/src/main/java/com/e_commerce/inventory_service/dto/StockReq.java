package com.e_commerce.inventory_service.dto;

public record StockReq(
        long productId,
        long quantity
) {
}