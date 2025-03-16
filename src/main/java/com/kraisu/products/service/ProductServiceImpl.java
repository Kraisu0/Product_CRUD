package com.kraisu.products.service;

import com.kraisu.products.DTO.ProductDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.exceptions.ProductNotFoundException;
import com.kraisu.products.mapper.ProductMapper;
import com.kraisu.products.model.Category;
import com.kraisu.products.model.Product;
import com.kraisu.products.repository.CategoryRepository;
import com.kraisu.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final BlockListService blockListService;
    private final ProductHistoryService productHistoryService;

    private void validateNotNullProductDTOFields(ProductDTO productDTO) {
        if (productDTO.getName() == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }
        if (productDTO.getPoints() == null) {
            throw new IllegalArgumentException("Product points cannot be null");
        }
        if (productDTO.getPrice() == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }
        if (productDTO.getQuantity() == null) {
            throw new IllegalArgumentException("Product quantity cannot be null");
        }
        if (productDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("Product category cannot be null");
        }
    }

    private void validateProductDTO(ProductDTO product, Category oldCategory) {
        if (product.getName() != null && !product.getName().matches("^[a-zA-Z0-9]{2,50}$")) {
            throw new IllegalArgumentException("Product name should be between 2 and 50 characters and contain only letters and numbers");
        }
        if (blockListService.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product name contains blocked words from BlockList");
        }
        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with name " + product.getName() + " already exists");
        }

        Category category = product.getCategoryId() == null ? oldCategory : categoryService.getCategory(product.getCategoryId());
        if (product.getPrice() != null && (product.getPrice() < category.getMinPrice() || product.getPrice() > category.getMaxPrice())) {
            throw new IllegalArgumentException("Product price should be between " + category.getMinPrice() + " and " + category.getMaxPrice());
        }
        if (product.getQuantity() != null && product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        validateNotNullProductDTOFields(productDTO);
        validateProductDTO(productDTO, null);
        Product newProduct = productMapper.mapToEntity(productDTO, categoryService.getCategory(productDTO.getCategoryId()));
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(UUID id, ProductDTO productDTO) {
        Product product = getProduct(id);
        validateProductDTO(productDTO, product.getCategory());
        LocalDateTime now = LocalDateTime.now();
        productHistoryService.createProductHistory(productDTO, product, now);
        UUID categoryId = productDTO.getCategoryId() == null ? product.getCategory().getId() : productDTO.getCategoryId();
        productMapper.updateProduct(product, productDTO, categoryService.getCategory(categoryId));
        return productRepository.save(product);
    }

    @Override
    public MessageResponse deleteProduct(UUID id) {
        productRepository.deleteById(id);
        return new MessageResponse("Product with id: " + id + " deleted successfully");
    }
}
