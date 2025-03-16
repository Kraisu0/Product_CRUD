package com.kraisu.products.repository;

import com.kraisu.products.model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, UUID> {
    Optional<ProductHistory> findByProductId(UUID productId);
}
