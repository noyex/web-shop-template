package com.noyex.productservice.service;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.DTOs.BrandDTO;
import com.noyex.productservice.exception.ResourceNotFoundException;
import com.noyex.productservice.repository.BrandRepository;
import com.noyex.productservice.service.interfaces.IBrandService;
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
                throw new ResourceNotFoundException("File not found with ID: " + brandDTO.getLogoFileId());
            }
            throw new ResourceNotFoundException("Error checking file: " + e.getMessage());
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

    @Override
    public Brand updateBrand(Long id, BrandDTO brandDTO) {
        String fileUrl = "http://localhost:8084/files/brand_logos/" + brandDTO.getLogoFileId();
        try {
            restTemplate.headForHeaders(fileUrl);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("File not found with ID: " + brandDTO.getLogoFileId());
            }
            throw new RuntimeException("Error checking file: " + e.getMessage());
        }

        Brand updatedBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with ID: " + id));

        updatedBrand.setName(brandDTO.getName());
        updatedBrand.setDescription(brandDTO.getDescription());
        updatedBrand.setLogoFileId(brandDTO.getLogoFileId());
        return brandRepository.save(updatedBrand);
    }
}
