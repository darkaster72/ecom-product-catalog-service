package io.github.darkaster.product_catalog_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
