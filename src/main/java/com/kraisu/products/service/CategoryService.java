package com.kraisu.products.service;

import com.kraisu.products.DTO.CategoryDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(UUID id);
    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategory(UUID id, CategoryDTO categoryDTO);
    MessageResponse deleteCategory(UUID id);
}
