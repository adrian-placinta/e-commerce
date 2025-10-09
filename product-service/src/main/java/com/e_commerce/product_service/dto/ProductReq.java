package com.e_commerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {
    private String productName;
    private String productDescription;
    private BigDecimal price;
}
