package com.e_commerce.inventory_service.repository;

import com.e_commerce.inventory_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(long productId);

    boolean existsByProductId(long productId);

    @Transactional
    void deleteByProductId(long productId);
}