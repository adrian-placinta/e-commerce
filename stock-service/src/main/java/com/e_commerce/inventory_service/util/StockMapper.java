package com.e_commerce.inventory_service.util;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;
import com.e_commerce.inventory_service.model.Stock;

public class StockMapper {

    public static Stock toEntity(final StockReq stockReqDto) {
        return Stock.builder()
                .productId(stockReqDto.productId())
                .quantity(stockReqDto.quantity())
                .build();
    }

    public static StockRes toRes(final Stock entity) {
        return new StockRes(
                entity.getStockId(),
                entity.getProductId(),
                entity.getQuantity()
        );
    }

}