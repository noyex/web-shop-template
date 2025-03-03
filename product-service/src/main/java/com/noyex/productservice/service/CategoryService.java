package com.noyex.productservice.service;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.CategoryDTO;
import com.noyex.productservice.entity.GeneralCategory;
import com.noyex.productservice.repository.CategoryRepository;
import com.noyex.productservice.repository.GeneralCategoryRepository;
import com.noyex.productservice.service.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final GeneralCategoryRepository generalCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, GeneralCategoryRepository generalCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.generalCategoryRepository = generalCategoryRepository;
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        if(categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        boolean exists = categoryRepository.existsByName(categoryDTO.getName());
        if(exists) {
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());
        GeneralCategory generalCategory = generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())
                .orElseThrow(() -> new RuntimeException("General category not found"));
        category.setGeneralCategory(generalCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryDTO.getName());
            category.setGeneralCategory(generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())
                    .orElseThrow(() -> new RuntimeException("General category not found")));
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Category not found");
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
