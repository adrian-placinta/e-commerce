package com.e_commerce.product_service.service.impl;

import com.e_commerce.product_service.dto.ProductReq;
import com.e_commerce.product_service.dto.ProductRes;
import com.e_commerce.product_service.exception.ProductNotFoundException;
import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.util.ProductMapper;
import com.e_commerce.product_service.repository.ProductRepository;
import com.e_commerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "PRODUCTS_MAP", key = "{#pageNo, #pageSize}")
    public List<ProductRes> getAllProducts(int pageNo, int pageSize) {
        return productRepository
                .findAll(PageRequest.of(pageNo, pageSize))
                .map(ProductMapper::toProductRes)
                .getContent();
    }

    @Override
    @CachePut(value = "PRODUCT_MAP", key = "#result.productId")
    @CacheEvict(value = "PRODUCTS_MAP", allEntries = true)
    public ProductRes addProduct(final ProductReq productReq) {
        return ProductMapper
                .toProductRes(productRepository.save(ProductMapper.toProduct(productReq)));
    }

    @Override
    @Transactional
  //  @CachePut(value = "PRODUCT_MAP", key = "#id")
 //   @CacheEvict(value = "PRODUCTS_MAP", allEntries = true)
    public ProductRes updateProduct(final Long id, final ProductReq productReq) throws ProductNotFoundException {
        var product = findProductById(id);

        product.setProductDescription(productReq.getProductDescription());
        product.setProductName(productReq.getProductName());
        product.setPrice(productReq.getPrice());

        return ProductMapper.toProductRes(productRepository.save(product));
    }

    @Override
//@Cacheable(value = "PRODUCT_MAP", key = "#id")
    public ProductRes getProductById(final Long id) {
        return productRepository
                .findById(id)
                .map(ProductMapper::toProductRes)
                .orElseThrow(() -> new ProductNotFoundException(MessageFormat.format(productNotFoundMessageTemplate, id)));
    }


    @Override
 //   @Caching(evict = {@CacheEvict(cacheNames = "PRODUCT_MAP", key = "#id", beforeInvocation = true),
  //          @CacheEvict(cacheNames = "PRODUCTS_MAP", allEntries = true)}
   // )
    public void deleteProductById(final Long id) {
        productRepository.deleteById(id);
    }

    private Product findProductById(final Long id) throws ProductNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(MessageFormat.format(productNotFoundMessageTemplate, id)));
    }
}
