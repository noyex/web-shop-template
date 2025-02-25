package com.noyex.productservice.enitity.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long brandId;
}
