package com.e_commerce.inventory_service.repository;

import com.e_commerce.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(long productId);

    void deleteByProductId(long productId);
}
