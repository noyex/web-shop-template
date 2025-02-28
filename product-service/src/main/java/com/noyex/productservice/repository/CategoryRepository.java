package com.noyex.productservice.repository;

import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.GeneralCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);
}
