package id.ac.ui.id.cs.adprog.eshop.controller;

import id.ac.ui.id.cs.adprog.eshop.model.Product;
import id.ac.ui.id.cs.adprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("c16a6486-16ed-4b8c-9373-f5db2a4e64b6");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(5);

        productList = Arrays.asList(product, product2);
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/create")
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testEditProductPage() throws Exception {
        when(productService.findOne(anyString())).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));

        verify(productService, times(1)).findOne(anyString());
    }

    @Test
    void testEditProductPost() throws Exception {
        when(productService.edit(anyString(), any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/edit/{id}", "eb558e9f-1c39-460e-8860-71af6af63bd6")
                        .param("productName", "Updated Product")
                        .param("productQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).edit(anyString(), any(Product.class));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        when(productService.delete(anyString())).thenReturn(product);

        mockMvc.perform(post("/product/delete/{id}", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete(anyString());
    }

    @Test
    void testProductListPage() throws Exception {
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productList"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", productList));

        verify(productService, times(1)).findAll();
    }
}