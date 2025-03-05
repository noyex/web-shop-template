package com.noyex.productservice.service;

import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.CategoryDTO;
import com.noyex.productservice.entity.GeneralCategory;
import com.noyex.productservice.exception.CategoryNotFoundException;
import com.noyex.productservice.exception.GeneralCategoryNotFoundException;
import com.noyex.productservice.exception.ProductNameExistsException;
import com.noyex.productservice.repository.CategoryRepository;
import com.noyex.productservice.repository.GeneralCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private GeneralCategoryRepository generalCategoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;
    private Category category;
    private GeneralCategory generalCategory;

    @BeforeEach
    public void setUp() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test Category");
        categoryDTO.setGeneralCategoryId(1L);

        generalCategory = new GeneralCategory();
        generalCategory.setId(categoryDTO.getGeneralCategoryId());
        generalCategory.setName("Test General Category");

        category = new Category();
        category.setName(categoryDTO.getName());
        category.setGeneralCategory(generalCategory);
    }

    @Test
    public void testCreateCategory_Success() {
        when(generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())).thenReturn(Optional.of(generalCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getGeneralCategory(), result.getGeneralCategory());
        assertEquals(category.getGeneralCategory().getId(), result.getGeneralCategory().getId());
        assertEquals(generalCategory.getName(), result.getGeneralCategory().getName());
        verify(generalCategoryRepository).findById(any(Long.class));
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void testCreateCategory_GeneralCategoryNotFound() {
        when(generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())).thenReturn(Optional.empty());

        assertThrows(GeneralCategoryNotFoundException.class, () -> {
            categoryService.createCategory(categoryDTO);
        });

        verify(generalCategoryRepository).findById(any(Long.class));
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testCreateCategory_CategoryAlreadyExists() {
        when(categoryRepository.existsByName(categoryDTO.getName())).thenReturn(true);

        assertThrows(ProductNameExistsException.class, () -> {
            categoryService.createCategory(categoryDTO);
        });

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testCreateCategory_NameNull() {
        categoryDTO.setName(null);

        assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(categoryDTO);
        });

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testCreateCategory_NameEmpty() {
        categoryDTO.setName("");

        assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(categoryDTO);
        });

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testGetCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getGeneralCategory(), result.getGeneralCategory());
        assertEquals(category.getProducts(), result.getProducts());
        verify(categoryRepository).findById(any(Long.class));
    }

    @Test
    public void testGetCategoryById_CategoryNotFound(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.getCategoryById(1L);
        });

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testGetAllCategories_Success(){
        List<Category> categories = Arrays.asList(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(categoryRepository).findAll();
    }

    @Test
    public void testUpdateCategory_Success(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())).thenReturn(Optional.of(generalCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.updateCategory(1L, categoryDTO);

        assertNotNull(result);
        assertEquals(categoryDTO.getName(), result.getName());
        assertEquals(categoryDTO.getGeneralCategoryId(), result.getGeneralCategory().getId());
        verify(categoryRepository).findById(1L);
        verify(generalCategoryRepository).findById(categoryDTO.getGeneralCategoryId());
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void testUpdateCategory_GeneralCategoryNotFound(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(generalCategoryRepository.findById(categoryDTO.getGeneralCategoryId())).thenReturn(Optional.empty());

        assertThrows(GeneralCategoryNotFoundException.class, () -> {
            categoryService.updateCategory(1L, categoryDTO);
        });

        verify(categoryRepository).findById(1L);
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testUpdateCategory_CategoryNotFound(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.updateCategory(1L, categoryDTO);
        });
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testDeleteCategory_Success(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        verify(categoryRepository).deleteById(1L);
    }

    @Test
    public void testDeleteCategory_CategoryNotFound(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.deleteCategory(1L);
        });
        verify(categoryRepository).findById(1L);
    }
}
