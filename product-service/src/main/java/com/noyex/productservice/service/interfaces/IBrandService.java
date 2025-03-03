package com.noyex.productservice.service.interfaces;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.DTOs.BrandDTO;

import java.util.List;

public interface IBrandService {
    Brand createBrand(BrandDTO brandDTO);
    List<Brand> getAllBrands();
    void deleteBrand(Long id);
    Brand getBrandById(Long id);
    Brand updateBrand(Long id, BrandDTO brandDTO);
}
