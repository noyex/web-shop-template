package com.noyex.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnoreProperties({"categories", "products", "description"})
    private GeneralCategory generalCategory;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
