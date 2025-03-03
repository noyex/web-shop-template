package com.noyex.productservice.controller;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.DTOs.BrandDTO;
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

    @PostMapping("/add")
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO brand) {
        return ResponseEntity.ok(brandService.createBrand(brand));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@RequestBody BrandDTO brand, @PathVariable Long id) {
        return ResponseEntity.ok(brandService.updateBrand(id, brand));
    }

}
