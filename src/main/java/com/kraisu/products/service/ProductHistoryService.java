package com.kraisu.products.service;

import com.kraisu.products.DTO.ProductDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.Product;
import com.kraisu.products.model.ProductHistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductHistoryService {
    List<ProductHistory> getProductHistory();
    ProductHistory getProductHistory(UUID id);
    void createProductHistory(ProductDTO newProduct, Product oldProduct, LocalDateTime now);
    MessageResponse deleteProductHistory(UUID id);
}
