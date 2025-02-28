package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.enitity.GeneralCategory;
import com.noyex.productservice.enitity.Product;

import java.util.List;

public interface IGeneralCategoryService {
    GeneralCategory createGeneralCategory(GeneralCategoryDTO generalCategory);
    GeneralCategory getGeneralCategoryById(Long id);
    List<GeneralCategory> getAllGeneralCategories();
    GeneralCategory updateGeneralCategory(GeneralCategoryDTO generalCategory, Long id);
    void deleteGeneralCategory(Long id);
    List<Product> getProductsByGeneralCategoryId(Long id);
    List<Category> getCategoriesByGeneralCategoryId(Long id);
}
