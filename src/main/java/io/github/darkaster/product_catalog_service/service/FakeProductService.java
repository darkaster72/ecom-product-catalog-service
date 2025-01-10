package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.client.FakeStoreClient;
import io.github.darkaster.product_catalog_service.dto.FakeStoreProductDto;
import io.github.darkaster.product_catalog_service.dto.ProductDto;
import io.github.darkaster.product_catalog_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FakeProductService implements ProductService {
    private final FakeStoreClient fakeStoreClient;
    private final ProductMapper productMapper;

    public FakeProductService(FakeStoreClient fakeStoreClient, ProductMapper productMapper) {
        this.fakeStoreClient = fakeStoreClient;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProducts() {
        return Objects.requireNonNull(fakeStoreClient.getProducts().getBody()).stream().map(productMapper::toEntity).toList();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(fakeStoreClient.getProductById(productId).getBody()).map(productMapper::toEntity);
    }

    @Override
    public Product createProduct(ProductDto request) {
        var entity = productMapper.toEntity(request);
        return entity;
    }

    @Override
    public Product updateProduct(Long id, ProductDto request) {
        var entity = productMapper.toEntity(request);
        var body = productMapper.toFakeDto(entity);
        var updated = fakeStoreClient.updateProductById(id, body).getBody();
        return productMapper.toEntity(updated);
    }
}
