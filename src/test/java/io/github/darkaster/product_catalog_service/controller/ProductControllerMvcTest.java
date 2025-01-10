package io.github.darkaster.product_catalog_service.controller;

import io.github.darkaster.product_catalog_service.controller.ProductController;
import io.github.darkaster.product_catalog_service.service.ProductMapper;
import io.github.darkaster.product_catalog_service.service.StorageProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerMvcTest {

    @MockitoBean
    private StorageProductService productService;

    @MockitoBean
    private ProductMapper productMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetMethodSuccess() throws Exception {
        // arrange
        var idCaptor = ArgumentCaptor.forClass(Long.class);

        // act
        mvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());

        // assert
        verify(productService.getProductById(idCaptor.capture()));  
    }
}