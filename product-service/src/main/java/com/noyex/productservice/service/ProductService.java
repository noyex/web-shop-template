package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Brand;
import com.noyex.productservice.enitity.Category;
import com.noyex.productservice.enitity.DTOs.ProductDTO;
import com.noyex.productservice.enitity.Product;
import com.noyex.productservice.repository.BrandRepository;
import com.noyex.productservice.repository.CategoryRepository;
import com.noyex.productservice.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        Optional<Brand> brand = brandRepository.findById(productDTO.getBrandId());
        if (brand.isEmpty()){
            throw new RuntimeException("Brand not found");
        }
        product.setBrand(brand.get());
        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        product.setCategory(category.get());
        product.setGeneralCategory(category.get().getGeneralCategory());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
