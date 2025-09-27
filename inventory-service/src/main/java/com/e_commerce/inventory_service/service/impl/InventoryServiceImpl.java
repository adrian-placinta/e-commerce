package com.e_commerce.inventory_service.service.impl;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;
import com.e_commerce.inventory_service.exception.StockNotFoundException;
import com.e_commerce.inventory_service.model.Inventory;
import com.e_commerce.inventory_service.repository.InventoryRepository;
import com.e_commerce.inventory_service.service.InventoryService;
import com.e_commerce.inventory_service.util.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Value("${stock.not-found.message}")
    private String stockNotFoundMessage;

    @Override
    public List<InventoryRes> updateInventoryStockQuantity(List<InventoryReq> inventoryReqs) {

        List<Inventory> inventoryStocksDb = inventoryReqs.stream()
                .map(inventoryReq -> updateInventoryStockQuantity(inventoryReq.productId(),
                        inventoryReq.quantity())).toList();

        return inventoryRepository
                .saveAll(inventoryStocksDb)
                .stream()
                .map(InventoryMapper::toInventoryResponse)
                .toList();
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

    private Inventory getInventoryStockById(long productId) throws StockNotFoundException {
        return inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(MessageFormat.format(stockNotFoundMessage, productId)));
    }

    private Inventory updateInventoryStockQuantity(long productId, long quantity) {
        Inventory inventoryStock = getInventoryStockById(productId);
        if (inventoryStock.getQuantity() + quantity < 0) {
            throw new RuntimeException("bla bla");
        }
        inventoryStock.setQuantity(quantity);

        return inventoryStock;
    }
}
