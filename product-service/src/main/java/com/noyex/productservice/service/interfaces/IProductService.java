package com.noyex.productservice.service.interfaces;


import com.noyex.productservice.entity.DTOs.ProductDTO;
import com.noyex.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductDTO productDTO);
//    List<Product> getProductsByCategoryId(Long categoryId);
//    List<Product> getProductsByGeneralCategoryId(Long generalCategoryId);
//    List<Product> getProductsByBestSellerTrue();
//    List<Product> getProductsByNewTrue();
//    List<Product> getProductsByOnSaleTrue();
//    List<Product> getProductsByBrandId(Long brandId);
}
