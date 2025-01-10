package io.github.darkaster.product_catalog_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class ProductDto {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private String imageUrl;
    @Positive
    private Double price;
    @NonNull
    private CategoryDto category;
}
