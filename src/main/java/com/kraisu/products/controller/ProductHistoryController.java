package com.kraisu.products.controller;

import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.ProductHistory;
import com.kraisu.products.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products-history")
@RequiredArgsConstructor
public class ProductHistoryController {
    private final ProductHistoryService productHistoryService;

    @GetMapping
    public ResponseEntity<List<ProductHistory>> getProductsHistory() {
        return ResponseEntity.ok(productHistoryService.getProductHistory());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductHistory> getProductHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(productHistoryService.getProductHistory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteProductHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(productHistoryService.deleteProductHistory(id));
    }
}
