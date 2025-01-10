package io.github.darkaster.product_catalog_service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    @Column(columnDefinition = "text")
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String imageUrl;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
