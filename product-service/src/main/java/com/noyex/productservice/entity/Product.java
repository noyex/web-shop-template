package com.noyex.productservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

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
    private int stock;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Brand brand;

    @ManyToOne
    @JsonIgnoreProperties({"products", "generalCategory"})
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"products", "categories", "generalCategory"})
    private GeneralCategory generalCategory;
}
