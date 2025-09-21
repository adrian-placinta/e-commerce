package com.e_commerce.inventory_service.service.impl;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;
import com.e_commerce.inventory_service.model.Inventory;
import com.e_commerce.inventory_service.repository.InventoryRepository;
import com.e_commerce.inventory_service.service.InventoryService;
import com.e_commerce.inventory_service.util.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public InventoryRes updateInventoryStockQuantity(long productId, long quantity) {

        var inventory = getInventoryStockById(productId);
        inventory.setQuantity(quantity);

        return InventoryMapper
                .toInventoryResponse(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryRes addNewInventoryStock(InventoryReq inventoryReq) {
        return InventoryMapper
                .toInventoryResponse(inventoryRepository.save(InventoryMapper
                        .toInventoryRes(inventoryReq)));
    }

    @Override
    public void deleteInventoryStock(long productId) {
        inventoryRepository.deleteByProductId(productId);
    }

    @Override
    public InventoryRes getInventoryStock(long productId) {
        return InventoryMapper.toInventoryResponse(getInventoryStockById(productId));
    }

    private Inventory getInventoryStockById(long productId) {
        return inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Test"));
    }
}
