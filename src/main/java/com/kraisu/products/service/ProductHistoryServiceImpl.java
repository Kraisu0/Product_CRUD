package com.kraisu.products.service;

import com.kraisu.products.DTO.ProductDTO;
import com.kraisu.products.exceptions.BlockListNotFoundExceptions;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.FieldName;
import com.kraisu.products.model.Product;
import com.kraisu.products.model.ProductHistory;
import com.kraisu.products.repository.ProductHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductHistoryServiceImpl implements ProductHistoryService {
    private final ProductHistoryRepository productHistoryRepository;
    private final CategoryService categoryService;

    @Override
    public List<ProductHistory> getProductHistory() {
        return productHistoryRepository.findAll();
    }

    @Override
    public ProductHistory getProductHistory(UUID id) {
        return productHistoryRepository.findById(id).orElseThrow(() -> new BlockListNotFoundExceptions(id));
    }

    @Override
    public void createProductHistory(ProductDTO newProduct, Product oldProduct, LocalDateTime now) {
        if (newProduct.getName() != null && !newProduct.getName().equals(oldProduct.getName())) {
            saveProductHistory(oldProduct, FieldName.NAME, newProduct.getName(), oldProduct.getName(), now);
        }
        if (newProduct.getPoints() != null && !newProduct.getPoints().equals(oldProduct.getPoints())) {
            saveProductHistory(oldProduct, FieldName.POINTS, newProduct.getPoints().toString(), oldProduct.getPoints().toString(), now);
        }
        if (newProduct.getPrice() != null && !newProduct.getPrice().equals(oldProduct.getPrice())) {
            saveProductHistory(oldProduct, FieldName.PRICE, newProduct.getPrice().toString(), oldProduct.getPrice().toString(), now);
        }
        if (newProduct.getCategoryId() != null && !newProduct.getCategoryId().equals(oldProduct.getCategory().getId())) {
            saveProductHistory(oldProduct, FieldName.CATEGORY, categoryService.getCategory(newProduct.getCategoryId()).getName(), oldProduct.getCategory().getName(), now);
        }
        if (newProduct.getQuantity() != null && !newProduct.getQuantity().equals(oldProduct.getQuantity())) {
            saveProductHistory(oldProduct, FieldName.QUANTITY, newProduct.getQuantity().toString(), oldProduct.getQuantity().toString(), now);
        }
    }

    private void saveProductHistory(Product product, FieldName fieldName, String newValue, String oldValue, LocalDateTime now) {
        productHistoryRepository.save(ProductHistory.builder().product(product).fieldName(fieldName).newValue(newValue).oldValue(oldValue).createdAt(now).build());
    }

    @Override
    public MessageResponse deleteProductHistory(UUID id) {
        productHistoryRepository.deleteById(id);
        return new MessageResponse("Deleted product history with id: " + id);
    }
}
