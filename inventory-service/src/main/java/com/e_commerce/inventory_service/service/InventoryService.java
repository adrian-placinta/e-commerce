package com.e_commerce.inventory_service.service;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;

import java.util.List;

public interface InventoryService {
    List<InventoryRes> updateInventoryStockQuantity(List<InventoryReq> inventoryReqs);

    InventoryRes addNewInventoryStock(InventoryReq inventoryReq);

    void deleteInventoryStock(long productId);

    InventoryRes getInventoryStock(long productId);
}
