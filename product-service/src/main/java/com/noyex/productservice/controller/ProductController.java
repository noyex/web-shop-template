package com.noyex.productservice.controller;

import com.noyex.productservice.entity.DTOs.ProductDTO;
import com.noyex.productservice.entity.Product;
import com.noyex.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product, @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

//    @GetMapping("/all/category/{categoryId}")
//    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
//        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
//    }
//
//    @GetMapping("/all/general-category/{generalCategoryId}")
//    public ResponseEntity<List<Product>> getProductsByGeneralCategoryId(@PathVariable Long generalCategoryId) {
//        return ResponseEntity.ok(productService.getProductsByGeneralCategoryId(generalCategoryId));
//    }
//
//    @GetMapping("/all/bestseller")
//    public ResponseEntity<List<Product>> getProductsByBestSellerTrue() {
//        return ResponseEntity.ok(productService.getProductsByBestSellerTrue());
//    }
//
//    @GetMapping("/all/new")
//    public ResponseEntity<List<Product>> getProductsByNewTrue() {
//        return ResponseEntity.ok(productService.getProductsByNewTrue());
//    }
//
//    @GetMapping("/all/onsale")
//    public ResponseEntity<List<Product>> getProductsByOnSaleTrue() {
//        return ResponseEntity.ok(productService.getProductsByOnSaleTrue());
//    }
//
//    @GetMapping("/all/brand/{brandId}")
//    public ResponseEntity<List<Product>> getProductsByBrandId(@PathVariable Long brandId) {
//        return ResponseEntity.ok(productService.getProductsByBrandId(brandId));
//    }

}
