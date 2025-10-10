package com.e_commerce.inventory_service.service;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;
import com.e_commerce.inventory_service.model.Stock;

import java.util.List;

public interface StockService {

    List<StockRes> getAllStocks(int pageNo, int pageSize);

    StockRes getStockByProductId(long productId);

    List<StockRes> saveAllStocks(List<StockReq> stocks);

    void deleteStockByProductId(long productId);

    StockRes subtractFromStockAndSave(long productId, long quantity);

    StockRes addToStockAndSave(long productId, long quantity);

    List<StockRes> updateAllStocks(List<Stock> stocks);

    Stock subtractFromStock(final long quantity, final long productId);

    Stock addToStock(final long productId, final long quantityToAdd);
}
