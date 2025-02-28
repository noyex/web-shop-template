package com.noyex.productservice.repository;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.GeneralCategory;
import com.noyex.productservice.enitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralCategoryRepository extends JpaRepository<GeneralCategory, Long> {
    Boolean existsByName(String name);
    List<Product> findProductsById(Long id);
    List<Category> findCategoriesById(Long id);
}
