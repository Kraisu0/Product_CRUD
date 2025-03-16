package com.kraisu.products.service;

import com.kraisu.products.DTO.ProductDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(UUID id);
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(UUID id, ProductDTO productDTO);
    MessageResponse deleteProduct(UUID id);
}
