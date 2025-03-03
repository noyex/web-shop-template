package com.noyex.productservice.repository;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.GeneralCategory;
import com.noyex.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralCategoryRepository extends JpaRepository<GeneralCategory, Long> {
    Boolean existsByName(String name);
    List<Product> findProductsById(Long id);
    List<Category> findCategoriesById(Long id);
}
