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

}
