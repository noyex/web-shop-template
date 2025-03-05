package com.noyex.productservice.service.interfaces;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.entity.GeneralCategory;
import com.noyex.productservice.entity.Product;

import java.util.List;

public interface IGeneralCategoryService {
    GeneralCategory createGeneralCategory(GeneralCategoryDTO generalCategory);
    GeneralCategory getGeneralCategoryById(Long id);
    List<GeneralCategory> getAllGeneralCategories();
    GeneralCategory updateGeneralCategory(GeneralCategoryDTO generalCategory, Long id);
    void deleteGeneralCategory(Long id);
}
