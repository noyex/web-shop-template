package com.noyex.productservice.controller;

import com.noyex.productservice.enitity.Brand;
import com.noyex.productservice.enitity.DTOs.BrandDTO;
import com.noyex.productservice.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO brand) {
        return ResponseEntity.ok(brandService.createBrand(brand));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
