package com.kraisu.products.repository;

import com.kraisu.products.model.BlockList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlockListRepository extends JpaRepository<BlockList, UUID> {
    boolean existsByName(String name);
}
