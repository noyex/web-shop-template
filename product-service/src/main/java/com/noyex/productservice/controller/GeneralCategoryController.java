package com.noyex.productservice.controller;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.enitity.GeneralCategory;
import com.noyex.productservice.enitity.Product;
import com.noyex.productservice.repository.GeneralCategoryRepository;
import com.noyex.productservice.service.GeneralCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/general-categories")
public class GeneralCategoryController {

    private final GeneralCategoryService generalCategoryService;

    public GeneralCategoryController(GeneralCategoryService generalCategoryService) {
        this.generalCategoryService = generalCategoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralCategory> addGeneralCategory(@RequestBody GeneralCategoryDTO generalCategory) {
        return ResponseEntity.ok(generalCategoryService.createGeneralCategory(generalCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralCategory> getGeneralCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(generalCategoryService.getGeneralCategoryById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeneralCategory>> getAllGeneralCategories() {
        return ResponseEntity.ok(generalCategoryService.getAllGeneralCategories());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<GeneralCategory> updateGeneralCategory(@RequestBody GeneralCategoryDTO generalCategory, @PathVariable Long id) {
        return ResponseEntity.ok(generalCategoryService.updateGeneralCategory(generalCategory, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGeneralCategory(@PathVariable Long id) {
        generalCategoryService.deleteGeneralCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProductsByGeneralCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(generalCategoryService.getProductsByGeneralCategoryId(id));
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<Category>> getCategoriesByGeneralCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(generalCategoryService.getCategoriesByGeneralCategoryId(id));
    }

}
