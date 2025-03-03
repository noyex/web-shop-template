package com.noyex.productservice.entity.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO {

    private String name;
    private String description;
    private Long logoFileId;
}
