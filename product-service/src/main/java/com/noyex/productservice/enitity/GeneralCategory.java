package com.noyex.productservice.enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class GeneralCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "generalCategory", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "generalCategory", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
