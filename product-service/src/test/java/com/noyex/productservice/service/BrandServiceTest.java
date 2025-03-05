package com.noyex.productservice.service;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.DTOs.BrandDTO;
import com.noyex.productservice.exception.BrandNotFoundException;
import com.noyex.productservice.exception.ResourceNotFoundException;
import com.noyex.productservice.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BrandService brandService;

    private BrandDTO brandDTO;
    private Brand brand;

    @BeforeEach
    public void setUp() {
        brandDTO = new BrandDTO();
        brandDTO.setName("Test Brand");
        brandDTO.setDescription("Test Description");
        brandDTO.setLogoFileId(1L);

        brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setDescription(brandDTO.getDescription());
        brand.setLogoFileId(brandDTO.getLogoFileId());
    }

    @Test
    public void testCreateBrand_Success() {
        when(restTemplate.headForHeaders(any(String.class))).thenReturn(null);
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandService.createBrand(brandDTO);

        assertNotNull(result);
        assertEquals(brand.getName(), result.getName());
        assertEquals(brand.getDescription(), result.getDescription());
        assertEquals(brand.getLogoFileId(), result.getLogoFileId());
        verify(restTemplate).headForHeaders(any(String.class));
        verify(brandRepository).save(any(Brand.class));
    }

    @Test
    public void testCreateBrand_FileNotFound(){
        // wywołanie restTemplate zawsze wyrzuca NOT_FOUND
        when(restTemplate.headForHeaders(anyString()))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // wywołuje i sprawdza czy wyjątek został rzucony
        assertThrows(ResourceNotFoundException.class, () -> {
            brandService.createBrand(brandDTO);
        });

        // sprawdza czy nigdy save nie zostało wykonane
        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    public void testGetAllBrands_Success() {
        List<Brand> brands = Arrays.asList(brand);
        when(brandRepository.findAll()).thenReturn(brands);

        List<Brand> result = brandService.getAllBrands();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(brandRepository).findAll();
    }

    @Test
    public void testGetBrandById_Success(){
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        Brand result = brandService.getBrandById(1L);

        assertNotNull(result);
        assertEquals(brand.getId(), result.getId());
        verify(brandRepository).findById(1L);
    }

    @Test
    public void testGetBrandById_NotFound(){
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> {
            brandService.getBrandById(1L);
        });

        verify(brandRepository).findById(1L);
    }
    

}
