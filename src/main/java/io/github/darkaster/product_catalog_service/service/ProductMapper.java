package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dto.FakeStoreProductDto;
import io.github.darkaster.product_catalog_service.dto.ProductDto;
import io.github.darkaster.product_catalog_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "name", source = "title")
    @Mapping(target = "imageUrl", source = "image")
    @Mapping(target = "category", ignore = true)
    Product toEntity(FakeStoreProductDto product);

    ProductDto toDto(Product product);

    @Mapping(target = "category", source = "category.name")
    FakeStoreProductDto toFakeDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductDto productDto);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto request);
}
