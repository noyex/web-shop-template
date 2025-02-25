package com.noyex.productservice.service;

import com.noyex.productservice.enitity.Brand;
import com.noyex.productservice.enitity.DTOs.BrandDTO;
import com.noyex.productservice.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;
    private final RestTemplate restTemplate;

    public BrandService(BrandRepository brandRepository, RestTemplate restTemplate) {
        this.brandRepository = brandRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Brand createBrand(BrandDTO brandDTO) {
        String fileUrl = "http://localhost:8084/files/brand_logos/" + brandDTO.getLogoFileId();
        try{
            restTemplate.headForHeaders(fileUrl);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("File not found");
            }
            throw new RuntimeException("Error checking file: " + e.getMessage());
        }

        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setDescription(brandDTO.getDescription());
        brand.setLogoFileId(brandDTO.getLogoFileId());

        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
    }
}
