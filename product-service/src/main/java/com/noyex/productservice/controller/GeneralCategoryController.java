package com.noyex.productservice.controller;

import com.noyex.productservice.entity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.entity.GeneralCategory;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralCategory> updateGeneralCategory(@RequestBody GeneralCategoryDTO generalCategory, @PathVariable Long id) {
        return ResponseEntity.ok(generalCategoryService.updateGeneralCategory(generalCategory, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGeneralCategory(@PathVariable Long id) {
        generalCategoryService.deleteGeneralCategory(id);
        return ResponseEntity.ok("General Category deleted successfully");
    }

}
