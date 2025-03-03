package com.noyex.productservice.service.interfaces;


import com.noyex.productservice.entity.DTOs.ProductDTO;
import com.noyex.productservice.entity.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductDTO productDTO);
}
