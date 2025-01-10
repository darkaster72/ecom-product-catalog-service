package io.github.darkaster.product_catalog_service.dao;

import io.github.darkaster.product_catalog_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
