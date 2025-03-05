package com.noyex.productservice.service;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.GeneralCategoryDTO;
import com.noyex.productservice.entity.GeneralCategory;
import com.noyex.productservice.entity.Product;
import com.noyex.productservice.exception.GeneralCategoryNotFoundException;
import com.noyex.productservice.exception.ProductNameExistsException;
import com.noyex.productservice.exception.ProductNotFoundException;
import com.noyex.productservice.repository.GeneralCategoryRepository;
import com.noyex.productservice.service.interfaces.IGeneralCategoryService;
import org.springframework.stereotype.Service;

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
            throw new ProductNameExistsException("General category already exists");
        }
        if(generalCategory.getName() == null || generalCategory.getName().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        GeneralCategory newGeneralCategory = new GeneralCategory();
        newGeneralCategory.setName(generalCategory.getName());
        newGeneralCategory.setDescription(generalCategory.getDescription());
        return generalCategoryRepository.save(newGeneralCategory);
    }

    @Override
    public GeneralCategory getGeneralCategoryById(Long id) {
        return generalCategoryRepository.findById(id)
                .orElseThrow(() -> new GeneralCategoryNotFoundException("General category not found"));
    }

    @Override
    public List<GeneralCategory> getAllGeneralCategories() {
        return generalCategoryRepository.findAll();
    }

    @Override
    public GeneralCategory updateGeneralCategory(GeneralCategoryDTO generalCategory, Long id) {
        Optional<GeneralCategory> generalCategoryOptional = generalCategoryRepository.findById(id);
        if(generalCategoryOptional.isEmpty()){
            throw new GeneralCategoryNotFoundException("General category not found");
        }
        boolean exists = generalCategoryRepository.existsByName(generalCategory.getName());
        if(exists){
            throw new ProductNameExistsException("General category already exists");
        }
        if(generalCategory.getName() == null || generalCategory.getName().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        GeneralCategory updatedGeneralCategory = generalCategoryOptional.get();
        updatedGeneralCategory.setName(generalCategory.getName());
        updatedGeneralCategory.setDescription(generalCategory.getDescription());
        return generalCategoryRepository.save(updatedGeneralCategory);
    }

    @Override
    public void deleteGeneralCategory(Long id) {
        GeneralCategory generalCategory = generalCategoryRepository.findById(id)
                .orElseThrow(() -> new GeneralCategoryNotFoundException("General category not found"));
        generalCategoryRepository.deleteById(id);
    }
}
