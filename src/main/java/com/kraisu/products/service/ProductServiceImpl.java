package com.kraisu.products.service;

import com.kraisu.products.model.Product;
import com.kraisu.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
