package com.e_commerce.product_service.util;

import com.e_commerce.product_service.dto.ProductReq;
import com.e_commerce.product_service.dto.ProductRes;
import com.e_commerce.product_service.model.Product;

public class ProductMapper {
    public static ProductRes toProductRes(Product product) {
        return new ProductRes(
                product.getId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getPrice());
    }

    public static Product toProduct(ProductReq productReq) {
        return Product.builder()
                .productName(productReq.getProductName())
                .productDescription(productReq.getProductDescription())
                .price(productReq.getPrice())
                .build();
    }
}
