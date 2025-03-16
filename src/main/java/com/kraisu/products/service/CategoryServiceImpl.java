package com.kraisu.products.service;

import com.kraisu.products.DTO.CategoryDTO;
import com.kraisu.products.exceptions.CategoryNotFoundException;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.mapper.CategoryMapper;
import com.kraisu.products.model.Category;
import com.kraisu.products.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private void validateCategoryDTO(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || !categoryDTO.getName().matches("^[a-zA-Z0-9]{2,50}$")) {
            throw new IllegalArgumentException("Category name should be between 3 and 20 characters and contain only letters and numbers");
        }
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new IllegalArgumentException("Category with name " + categoryDTO.getName() + " already exists");
        }
        if (categoryDTO.getMinPrice() == null || categoryDTO.getMinPrice() < 0) {
            throw new IllegalArgumentException("Category minimum price must be greater than 0. Provided value: " + categoryDTO.getMinPrice());
        }
        if (categoryDTO.getMaxPrice() == null || categoryDTO.getMaxPrice() < 0) {
            throw new IllegalArgumentException("Category maximum price must be greater than 0. Provided value: " + categoryDTO.getMaxPrice());
        }
        if(categoryDTO.getMinPrice() > categoryDTO.getMaxPrice()) {
            throw new IllegalArgumentException("Category minimum price must be less than maximum price. " +
                    "Provided values: minPrice= " + categoryDTO.getMinPrice() + " maxPrice= " + categoryDTO.getMaxPrice());
        }
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        validateCategoryDTO(categoryDTO);
        return categoryRepository.save(categoryMapper.mapToEntity(categoryDTO));
    }

    @Override
    public Category updateCategory(UUID id, CategoryDTO categoryDTO) {
        Category category = getCategory(id);
        validateCategoryDTO(categoryDTO);
        categoryMapper.updateCategory(category, categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public MessageResponse deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
        return new MessageResponse("Category with id " + id + " has been deleted");
    }
}
