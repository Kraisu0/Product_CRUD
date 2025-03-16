package com.kraisu.products.mapper;

import com.kraisu.products.DTO.CategoryDTO;
import com.kraisu.products.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category mapToEntity(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(@MappingTarget Category category, CategoryDTO categoryDTO);
}
