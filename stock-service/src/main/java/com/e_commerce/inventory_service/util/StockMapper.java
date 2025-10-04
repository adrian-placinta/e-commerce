package com.e_commerce.inventory_service.util;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;
import com.e_commerce.inventory_service.model.Stock;

public class StockMapper {

    public static Stock toEntity(final StockReq dto) {
        return Stock.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }

    public static StockRes toRes(final Stock entity) {
        return StockRes.builder()
                .stockId(entity.getStockId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .build();
    }
}