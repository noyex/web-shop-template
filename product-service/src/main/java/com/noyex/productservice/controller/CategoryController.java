package com.noyex.productservice.controller;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.CategoryDTO;
import com.noyex.productservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDTO category, @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
}
