package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.DTOs.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
