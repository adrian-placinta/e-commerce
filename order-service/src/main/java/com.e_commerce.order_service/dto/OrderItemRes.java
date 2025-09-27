package com.e_commerce.order_service.dto;

public record OrderItemRes(Long id, Long productId, Long quantity, Long orderId) {
}
