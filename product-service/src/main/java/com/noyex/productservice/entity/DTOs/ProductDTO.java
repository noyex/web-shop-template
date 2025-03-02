package com.noyex.productservice.entity.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private double discount;
    private int stock;
    private Long brandId;
    private Long categoryId;
    private Long generalCategoryId;
}
