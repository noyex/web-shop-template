package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.enitity.GeneralCategory;
import com.noyex.productservice.enitity.Product;
import com.noyex.productservice.repository.GeneralCategoryRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralCategoryService implements IGeneralCategoryService {

    private final GeneralCategoryRepository generalCategoryRepository;

    public GeneralCategoryService(GeneralCategoryRepository generalCategoryRepository) {
        this.generalCategoryRepository = generalCategoryRepository;
    }

    @Override
    public GeneralCategory createGeneralCategory(GeneralCategoryDTO generalCategory) {
        boolean exists = generalCategoryRepository.existsByName(generalCategory.getName());
        if(exists){
            throw new RuntimeException("General category already exists");
        }
        if(generalCategory.getName() == null || generalCategory.getName().isEmpty()){
            throw new RuntimeException("Name is required");
        }
        GeneralCategory newGeneralCategory = new GeneralCategory();
        newGeneralCategory.setName(generalCategory.getName());
        newGeneralCategory.setDescription(generalCategory.getDescription());
        return generalCategoryRepository.save(newGeneralCategory);
    }

    @Override
    public GeneralCategory getGeneralCategoryById(Long id) {
        return generalCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("General category not found"));
    }

    @Override
    public List<GeneralCategory> getAllGeneralCategories() {
        return generalCategoryRepository.findAll();
    }

    @Override
    public GeneralCategory updateGeneralCategory(GeneralCategoryDTO generalCategory, Long id) {
        Optional<GeneralCategory> generalCategoryOptional = generalCategoryRepository.findById(id);
        if(generalCategoryOptional.isEmpty()){
            throw new RuntimeException("General category not found");
        }
        boolean exists = generalCategoryRepository.existsByName(generalCategory.getName());
        if(exists){
            throw new RuntimeException("General category already exists");
        }
        if(generalCategory.getName() == null || generalCategory.getName().isEmpty()){
            throw new RuntimeException("Name is required");
        }
        GeneralCategory updatedGeneralCategory = generalCategoryOptional.get();
        updatedGeneralCategory.setName(generalCategory.getName());
        updatedGeneralCategory.setDescription(generalCategory.getDescription());
        return generalCategoryRepository.save(updatedGeneralCategory);
    }

    @Override
    public void deleteGeneralCategory(Long id) {
        generalCategoryRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByGeneralCategoryId(Long id) {
        List<Product> products = generalCategoryRepository.findProductsById(id);
        if (products.isEmpty()){
            throw new RuntimeException("No products found");
        }
        return products;
    }

    @Override
    public List<Category> getCategoriesByGeneralCategoryId(Long id) {
        List<Category> categories = generalCategoryRepository.findCategoriesById(id);
        if (categories.isEmpty()){
            throw new RuntimeException("No categories found");
        }
        return categories;
    }
}
