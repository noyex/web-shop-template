package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Brand;

import java.util.List;

public interface IBrandService {
    Brand createBrand(Brand brand);
    List<Brand> getAllBrands();
    void deleteBrand(Long id);
    Brand getBrandById(Long id);
}
