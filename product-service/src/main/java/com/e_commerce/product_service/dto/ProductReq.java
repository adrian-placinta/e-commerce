package com.e_commerce.product_service.dto;

import java.math.BigDecimal;

public record ProductReq(String productName,
                         String productDescription,
                         BigDecimal price) {
};
