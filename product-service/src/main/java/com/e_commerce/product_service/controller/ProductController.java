package com.e_commerce.product_service.controller;

import com.e_commerce.product_service.dto.ProductReq;
import com.e_commerce.product_service.dto.ProductRes;
import com.e_commerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductRes>> getProducts(@RequestParam(defaultValue = "0", required = false) final int pageNo,
                                                        @RequestParam(defaultValue = "5", required = false) final int pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> getProduct(@PathVariable final Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductRes> addProduct(@RequestBody final ProductReq productReq) {
        return new ResponseEntity<>(productService.addProduct(productReq),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRes> updateProduct(@PathVariable final Long id,
                                                    @RequestBody final ProductReq productReq) {
        return ResponseEntity.ok(productService.updateProduct(id, productReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
