package com.e_commerce.product_service.service.impl;

import com.e_commerce.product_service.dto.ProductReq;
import com.e_commerce.product_service.dto.ProductRes;
import com.e_commerce.product_service.exception.ProductNotFoundException;
import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.util.ProductMapper;
import com.e_commerce.product_service.repository.ProductRepository;
import com.e_commerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${product.not.found.message}")
    private String productNotFoundMessageTemplate;

    private final ProductRepository productRepository;

    @Override
    public List<ProductRes> getAllProducts(int pageNo, int pageSize) {
        return productRepository
                .findAll(PageRequest.of(pageNo, pageSize))
                .map(ProductMapper::toProductRes)
                .getContent();
    }

    @Override
    public ProductRes addProduct(final ProductReq productReq) {
        return ProductMapper
                .toProductRes(productRepository.save(ProductMapper.toProduct(productReq)));
    }

    @Override
    @Transactional
    public ProductRes updateProduct(final Long id, final ProductReq productReq) throws ProductNotFoundException {
        var product = findProductById(id);

        product.setProductDescription(productReq.productDescription());
        product.setProductName(productReq.productName());
        product.setPrice(productReq.price());

        return ProductMapper.toProductRes(productRepository.save(product));
    }

    @Override
    public ProductRes getProductById(final Long id) {
        return productRepository
                .findById(id)
                .map(ProductMapper::toProductRes)
                .orElseThrow(() -> new ProductNotFoundException(MessageFormat.format(productNotFoundMessageTemplate, id)));
    }

    @Override
    public void deleteProductById(final Long id) {
        productRepository.deleteById(id);
    }

    private Product findProductById(final Long id) throws ProductNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(MessageFormat.format(productNotFoundMessageTemplate, id)));
    }
}
