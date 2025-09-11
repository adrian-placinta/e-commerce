package com.e_commerce.product_service.repository;

import com.e_commerce.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
