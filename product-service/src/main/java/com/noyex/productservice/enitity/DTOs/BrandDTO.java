package com.noyex.productservice.enitity.DTOs;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO {

    private String name;
    private String description;
    private Long logoFileId;
}
