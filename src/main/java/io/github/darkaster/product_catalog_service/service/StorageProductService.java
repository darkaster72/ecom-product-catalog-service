package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dao.ProductDao;
import io.github.darkaster.product_catalog_service.dto.ProductDto;
import io.github.darkaster.product_catalog_service.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StorageProductService implements ProductService {
    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public StorageProductService(ProductDao productDao, ProductMapper productMapper, CategoryService categoryService) {
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productDao.findById(productId);
    }

    @Override
    public Product createProduct(ProductDto request) {
        var product = productMapper.toEntity(request);
        var category = categoryService.updateOrCreate(request.getCategory());
        product.setCategory(category);
        return productDao.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDto request) {
        var product = getProductById(id).orElseThrow();
        productMapper.updateEntity(product, request);
        var category = categoryService.updateOrCreate(request.getCategory());
        product.setCategory(category);
        return productDao.save(product);
    }
}
