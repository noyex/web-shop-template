package com.noyex.productservice.repository;

import com.noyex.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
//    List<Product> findByCategoryId(Long categoryId);
//    List<Product> findByGeneralCategoryId(Long generalCategoryId);
//    List<Product> findByBestSellerTrue();
//    List<Product> findByNewArrivalTrue();
//    List<Product> findByOnSaleTrue();
//    List<Product> findByBrandId(Long brandId);
}
