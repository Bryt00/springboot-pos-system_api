package com.raven.central_pos.category.services;

import com.raven.central_pos.category.contracts.CategoryDto;
import com.raven.central_pos.core.exceptions.UserException;

import java.util.List;

public interface ICategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception;
    void deleteCategory (Long id) throws Exception;



}
