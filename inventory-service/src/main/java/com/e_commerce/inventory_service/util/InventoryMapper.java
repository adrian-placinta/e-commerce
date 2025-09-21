package com.e_commerce.inventory_service.util;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;
import com.e_commerce.inventory_service.model.Inventory;

public class InventoryMapper {
    public static InventoryRes toInventoryResponse(Inventory inventory) {
        return new InventoryRes(inventory.getId(), inventory.getProductId(), inventory.getQuantity());
    }

    public static Inventory toInventoryRes(InventoryReq inventoryReq) {
        return Inventory
                .builder()
                    .productId(inventoryReq.productId())
                    .quantity(inventoryReq.quantity())
                .build();
    }
}
