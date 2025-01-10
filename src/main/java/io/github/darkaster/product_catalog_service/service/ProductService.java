package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dto.ProductDto;
import io.github.darkaster.product_catalog_service.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();

    Optional<Product> getProductById(Long productId);

    Product createProduct(ProductDto request);

    Product updateProduct(Long id, ProductDto request);
}
