package com.noyex.productservice.enitity;

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
    @JsonIgnoreProperties({"categories", "products"})
    private GeneralCategory generalCategory;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"price", "stock", "brand", "category", "generalCategory"})
    private List<Product> products = new ArrayList<>();
}
