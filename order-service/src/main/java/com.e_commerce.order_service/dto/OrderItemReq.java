package com.e_commerce.order_service.dto;

public record OrderItemReq(Long id, Long productId, Long quantity, Long orderId) {
}