package com.e_commerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRes implements Serializable {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
}
