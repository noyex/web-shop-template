package com.noyex.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long logoFileId;
    private String description;
    @OneToMany(mappedBy = "brand", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
