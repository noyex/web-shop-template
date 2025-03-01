package com.noyex.productservice.service;


import com.noyex.productservice.enitity.DTOs.ProductDTO;
import com.noyex.productservice.enitity.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
}
