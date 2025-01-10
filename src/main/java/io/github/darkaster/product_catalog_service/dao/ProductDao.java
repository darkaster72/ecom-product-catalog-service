package io.github.darkaster.product_catalog_service.dao;

import io.github.darkaster.product_catalog_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
}
