package com.e_commerce.inventory_service.service;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;

public interface InventoryService {
    InventoryRes updateInventoryStockQuantity(long productId, long quantity);

    InventoryRes addNewInventoryStock(InventoryReq inventoryReq);

    void deleteInventoryStock(long productId);

    InventoryRes getInventoryStock(long productId);
}
