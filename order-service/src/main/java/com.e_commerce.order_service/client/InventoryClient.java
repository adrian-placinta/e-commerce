package com.e_commerce.order_service.client;

import com.e_commerce.order_service.dto.InventoryRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8082",
        path = "/api/v1/inventory"
)
public interface InventoryClient {

    @PutMapping("/{productId}")
    ResponseEntity<InventoryRes> updateStock(
            @PathVariable("productId") Long productId,
            @RequestParam("quantity") Long quantity
    );
}