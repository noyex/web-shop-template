package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Brand;
import com.noyex.productservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand createBrand(Brand brand) {
        return null;
    }

    @Override
    public List<Brand> getAllBrands() {
        return List.of();
    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public Brand getBrandById(Long id) {
        return null;
    }
}
