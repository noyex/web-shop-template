package com.noyex.productservice.service;


import com.noyex.productservice.enitity.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
