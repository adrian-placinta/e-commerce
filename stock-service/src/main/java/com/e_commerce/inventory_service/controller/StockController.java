package com.e_commerce.inventory_service.controller;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;
import com.e_commerce.inventory_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockRes>> getAllStocks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(stockService.getAllStocks(pageNo, pageSize));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<StockRes> getStockByProductId(@PathVariable long productId) {
        return ResponseEntity.ok(stockService.getStockByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<List<StockRes>> saveStock(@RequestBody List<StockReq> stockReqs) {
        return new ResponseEntity<>(stockService.saveAllStocks(stockReqs), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteStockByProductId(@PathVariable long productId) {
        stockService.deleteStockByProductId(productId);
        return ResponseEntity.noContent().build();
    }

}
