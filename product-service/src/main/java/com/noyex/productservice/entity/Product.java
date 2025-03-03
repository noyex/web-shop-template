package com.noyex.productservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private double discount;
    private int stock;
    private int sold;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Brand brand;

    @ManyToOne
    @JsonIgnoreProperties({"products", "generalCategory"})
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"products", "categories", "generalCategory"})
    private GeneralCategory generalCategory;

    private LocalDateTime createdAt;
    private boolean isBestSeller;
    private boolean isSoldOut;
    private boolean isOnSale;
    private boolean isNew;
    private boolean isAlmostSoldOut;
}
