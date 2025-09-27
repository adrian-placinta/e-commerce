package com.e_commerce.inventory_service.controller;

import com.e_commerce.inventory_service.dto.InventoryReq;
import com.e_commerce.inventory_service.dto.InventoryRes;
import com.e_commerce.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory/")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("{productId}")
    public ResponseEntity<InventoryRes> getInventoryStock(@PathVariable long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryStock(productId));
    }

    @PostMapping
    public ResponseEntity<InventoryRes> addNewInventoryStock(
            @RequestBody InventoryReq inventoryReq) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryService.addNewInventoryStock(inventoryReq));
    }

    @PutMapping()
    public ResponseEntity<List<InventoryRes>> updateInventoryStockQuantity(
            List<InventoryReq> inventoryReqs) {
        return ResponseEntity.ok(inventoryService.updateInventoryStockQuantity(inventoryReqs));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteInventoryStock(@PathVariable long productId) {
        inventoryService.deleteInventoryStock(productId);
        return ResponseEntity.noContent().build();
    }

}