package com.e_commerce.product_service.service;

import com.e_commerce.product_service.dto.ProductReq;
import com.e_commerce.product_service.dto.ProductRes;

import java.util.List;

public interface ProductService {
    List<ProductRes> getAllProducts(final int pageNo, final int pageSize);

    ProductRes addProduct(final ProductReq productReq);

    ProductRes updateProduct(final Long id, final ProductReq productReq);

    ProductRes getProductById(final Long id);

    void deleteProductById(final Long id);
}
