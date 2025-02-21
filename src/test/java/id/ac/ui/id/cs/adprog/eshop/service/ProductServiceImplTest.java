package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Product;
import id.ac.ui.id.cs.adprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Product product2 = new Product();
        product2.setProductId("8ffb4b07-3f0c-4765-9e6e-d38a06d06b98");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(5);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> results = productService.findAll();

        assertEquals(2, results.size());
        assertEquals(product.getProductId(), results.get(0).getProductId());
        assertEquals(product2.getProductId(), results.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindOne() {
        when(productRepository.findOne(anyString())).thenReturn(product);

        Product result = productService.findOne("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        verify(productRepository, times(1)).findOne(anyString());
    }

    @Test
    void testEdit() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        when(productRepository.edit(anyString(), any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.edit(product.getProductId(), updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getProductId(), result.getProductId());
        assertEquals(updatedProduct.getProductName(), result.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).edit(anyString(), any(Product.class));
    }

    @Test
    void testDelete() {
        when(productRepository.delete(anyString())).thenReturn(product);

        Product result = productService.delete(product.getProductId());

        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
        verify(productRepository, times(1)).delete(anyString());
    }
}