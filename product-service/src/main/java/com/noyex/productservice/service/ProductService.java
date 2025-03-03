package com.noyex.productservice.service;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.ProductDTO;
import com.noyex.productservice.entity.Product;
import com.noyex.productservice.repository.BrandRepository;
import com.noyex.productservice.repository.CategoryRepository;
import com.noyex.productservice.repository.ProductRepository;
import com.noyex.productservice.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product updatedProduct = productOptional.get();

        updatedProduct.setName(productDTO.getName());
        updatedProduct.setDescription(productDTO.getDescription());
        updatedProduct.setPrice(productDTO.getPrice());
        updatedProduct.setStock(productDTO.getStock());

        Optional<Brand> brand = brandRepository.findById(productDTO.getBrandId());
        if (brand.isEmpty()){
            throw new RuntimeException("Brand not found");
        }
        updatedProduct.setBrand(brand.get());

        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        updatedProduct.setCategory(category.get());

        updatedProduct.setGeneralCategory(category.get().getGeneralCategory());
        return productRepository.save(updatedProduct);
    }
}
