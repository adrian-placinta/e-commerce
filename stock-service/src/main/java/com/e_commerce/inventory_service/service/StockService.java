package com.e_commerce.inventory_service.service;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;

import java.util.List;

public interface StockService {

    List<StockRes> getAllStocks(int pageNo, int pageSize);

    StockRes getStockByProductId(final long productId);

    List<StockRes> saveStock(final List<StockReq> stocks);

    void deleteStockByProductId(final long productId);

    List<StockRes> decreaseStocks(List<StockReq> stockReqs);

    List<StockRes> increaseStocks(List<StockReq> stockReqs);
}