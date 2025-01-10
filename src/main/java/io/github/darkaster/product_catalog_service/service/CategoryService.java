package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dao.CategoryDao;
import io.github.darkaster.product_catalog_service.dto.CategoryDto;
import io.github.darkaster.product_catalog_service.model.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryDao categoryDao;

    public CategoryService(CategoryMapper categoryMapper, CategoryDao categoryDao) {
        this.categoryMapper = categoryMapper;
        this.categoryDao = categoryDao;
    }

    public Optional<Category> findById(Long id) {
        return categoryDao.findById(id);
    }

    public Category create(CategoryDto request) {
        var entity = categoryMapper.toEntity(request);
        return categoryDao.save(entity);
    }

    public Category updateOrCreate(CategoryDto request) {
        var entity = Optional.ofNullable(request.getId())
                .flatMap(categoryDao::findById)
                .orElseGet(() -> create(request));
        categoryMapper.updateEntity(entity, request);
        return categoryDao.save(entity);
    }

}
