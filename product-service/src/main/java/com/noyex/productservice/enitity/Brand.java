package com.noyex.productservice.enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String logo;
    private String description;
    @OneToMany(mappedBy = "brand", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
