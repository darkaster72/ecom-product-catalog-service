package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dto.CategoryDto;
import io.github.darkaster.product_catalog_service.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryDto dto);

}
