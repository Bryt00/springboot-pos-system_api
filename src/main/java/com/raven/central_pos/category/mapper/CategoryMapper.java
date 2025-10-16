package com.raven.central_pos.category.mapper;

import com.raven.central_pos.category.contracts.CategoryDto;
import com.raven.central_pos.category.model.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null? category.getStore().getId() : null)
                .build();
    }
}
