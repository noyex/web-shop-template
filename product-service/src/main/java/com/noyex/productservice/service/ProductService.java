package com.noyex.productservice.service;

import com.noyex.productservice.entity.Brand;
import com.noyex.productservice.entity.Category;
import com.noyex.productservice.entity.DTOs.ProductDTO;
import com.noyex.productservice.entity.Product;
import com.noyex.productservice.exception.BrandNotFoundException;
import com.noyex.productservice.exception.CategoryNotFoundException;
import com.noyex.productservice.exception.ProductNameExistsException;
import com.noyex.productservice.exception.ProductNotFoundException;
import com.noyex.productservice.repository.BrandRepository;
import com.noyex.productservice.repository.CategoryRepository;
import com.noyex.productservice.repository.ProductRepository;
import com.noyex.productservice.service.interfaces.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new ProductNameExistsException("Product with name '" + productDTO.getName() + "' already exists");
        }
        Product product = new Product();
        return createOrUpdateProduct(product, productDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = checkIfProductExists(id);

        if (!existingProduct.getName().equals(productDTO.getName()) &&
                productRepository.existsByName(productDTO.getName())) {
            throw new ProductNameExistsException("Product with name '" + productDTO.getName() + "' already exists");
        }
        return createOrUpdateProduct(existingProduct, productDTO);
    }

    private Product createOrUpdateProduct(Product product, ProductDTO productDto) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setDiscount(productDto.getDiscount());
        product.setPrice(calculatePriceWithDiscount(productDto));
        product.setStock(productDto.getStock());

        if (product.getId() == null) {
            product.setSold(0);
            product.setCreatedAt(LocalDateTime.now());
        }

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        product.setBrand(brand);

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        product.setCategory(category);
        product.setGeneralCategory(category.getGeneralCategory());

        product.setBestSeller(isProductBestseller(product));
        product.setSoldOut(isProductSoldOut(product));
        product.setOnSale(isProductOnSale(product));
        product.setNew(isNewProduct(product));
        product.setAlmostSoldOut(isAlmostSoldOut(product));

        return productRepository.save(product);
    }

    private double calculatePriceWithDiscount(ProductDTO productDto) {
        double discountedPrice = productDto.getPrice() - (productDto.getPrice() * productDto.getDiscount() / 100);

        double roundedPrice = Math.floor(discountedPrice) + 0.99;

        if (roundedPrice < 0.99) {
            roundedPrice = 0.99;
        }

        return roundedPrice;
    }


    private Product checkIfProductExists(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private static final int BESTSELLER_THRESHOLD = 30;
    private static final int ALMOST_SOLD_OUT_THRESHOLD = 5;
    private static final int NEW_PRODUCT_DAYS_THRESHOLD = 30;

    private boolean isProductBestseller(Product product) {
        return product.getSold() > BESTSELLER_THRESHOLD;
    }

    private boolean isProductSoldOut(Product product) {
        return product.getStock() == 0;
    }

    private boolean isProductOnSale(Product product) {
        return product.getDiscount() > 0;
    }

    private boolean isNewProduct(Product product) {
        if(product.getCreatedAt() == null){
            return false;
        }
        return product.getCreatedAt().isAfter(LocalDateTime.now().minusDays(NEW_PRODUCT_DAYS_THRESHOLD));
    }

    private boolean isAlmostSoldOut(Product product) {
        return product.getStock() > 0 && product.getStock() <= ALMOST_SOLD_OUT_THRESHOLD;
    }

}
