package io.github.darkaster.product_catalog_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Category extends BaseModel {
    @Column(columnDefinition = "text")
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
