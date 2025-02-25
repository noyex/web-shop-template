package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Brand;
import com.noyex.productservice.enitity.DTOs.BrandDTO;

import java.util.List;

public interface IBrandService {
    Brand createBrand(BrandDTO brandDTO);
    List<Brand> getAllBrands();
    void deleteBrand(Long id);
    Brand getBrandById(Long id);
}
