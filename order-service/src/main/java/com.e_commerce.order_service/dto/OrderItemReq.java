package com.e_commerce.order_service.dto;

import jakarta.annotation.Nullable;

public record OrderItemReq(Long id, Long productId, Long quantity, Long orderId) {
}