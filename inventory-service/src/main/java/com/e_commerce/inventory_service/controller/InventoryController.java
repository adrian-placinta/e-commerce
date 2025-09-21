package com.e_commerce.inventory_service.controller;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;
import com.e_commerce.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory/")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PutMapping("{productId}")
    public ResponseEntity<InventoryRes> updateInventoryStockQuantity(
            @PathVariable long productId,
            @RequestParam long quantity) {

        InventoryRes updatedInventory = inventoryService.updateInventoryStockQuantity(productId, quantity);
        return ResponseEntity.ok(updatedInventory);
    }

    @PostMapping
    public ResponseEntity<InventoryRes> addNewInventoryStock(
            @RequestBody InventoryReq inventoryReq) {

        InventoryRes createdInventory = inventoryService.addNewInventoryStock(inventoryReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteInventoryStock(@PathVariable long productId) {

        inventoryService.deleteInventoryStock(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<InventoryRes> getInventoryStock(@PathVariable long productId) {

        InventoryRes inventoryRes = inventoryService.getInventoryStock(productId);
        return ResponseEntity.ok(inventoryRes);
    }
}