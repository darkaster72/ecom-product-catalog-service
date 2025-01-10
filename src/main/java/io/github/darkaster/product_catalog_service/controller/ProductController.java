package io.github.darkaster.product_catalog_service.controller;

import io.github.darkaster.product_catalog_service.dto.ProductDto;
import io.github.darkaster.product_catalog_service.model.Product;
import io.github.darkaster.product_catalog_service.service.ProductMapper;
import io.github.darkaster.product_catalog_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts().stream().map(this::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(this::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("No product with id %d".formatted(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto request) {
        return ResponseEntity.ok(from(productService.createProduct(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto request) {
        return ResponseEntity.ok(from(productService.updateProduct(id, request)));
    }

    private ProductDto from(Product product) {
        return productMapper.toDto(product);
    }
}
