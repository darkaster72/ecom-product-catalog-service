package io.github.darkaster.product_catalog_service.service;

import io.github.darkaster.product_catalog_service.dao.ProductDao;
import io.github.darkaster.product_catalog_service.model.Category;
import io.github.darkaster.product_catalog_service.model.Product;
import io.github.darkaster.product_catalog_service.service.CategoryService;
import io.github.darkaster.product_catalog_service.service.ProductMapper;
import io.github.darkaster.product_catalog_service.service.StorageProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageProductServiceTest {
    @Mock
    private ProductDao productDao;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private StorageProductService storageProductService;

    @Test
    void testGetProducts() {
        var products = List.of(
                Product.builder().name("product1").build(),
                Product.builder().name("product2").build()
        );
        // arrange
        when(productDao.findAll()).thenReturn(products);
        when(productDao.findById(anyLong())).thenReturn(Optional.of(products.get(0)));

        // act
        var result = storageProductService.getProducts();
        // assert
        assertEquals(2, result.size());
    }

    @Test
    void testGet() {
        var response = new RestTemplateBuilder().build().getForObject("https://fakestoreapi.com/products/categories",
                String[].class);

        var categories = Stream.of(response).map(this::createCategory).collect(Collectors.toList());
        System.out.println(categories);
    }

    private Category createCategory(String s) {
        var category = new Category();
        category.setName(s);
        category.setId(new Random().nextLong());
        return category;
    }
}