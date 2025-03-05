package com.noyex.productservice.repository;

import com.noyex.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    List<Product> findByCategory_Id(Long categoryId);
    List<Product> findByGeneralCategory_Id(Long generalCategoryId);
    List<Product> findByIsBestSellerTrue();
    List<Product> findByIsNewArrivalTrue();
    List<Product> findByIsOnSaleTrue();
    List<Product> findByBrand_Id(Long brandId);
}
