package com.e_commerce.product_service.dto;

import java.math.BigDecimal;

public record ProductRes(Long productId,
                         String productName,
                         String productDescription,
                         BigDecimal price) {
};