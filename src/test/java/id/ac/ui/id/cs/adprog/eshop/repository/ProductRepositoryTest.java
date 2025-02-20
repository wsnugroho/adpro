package id.ac.ui.id.cs.adprog.eshop.repository;

import id.ac.ui.id.cs.adprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("aed0131d-ffdb-47bd-a7f6-424183c80188");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindOne() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findOne(product.getProductId());
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindOneIfNonExistent() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findOne("aed0131d-ffdb-47bd-a7f6-424183c80188");
        assertNull(foundProduct);
    }

    @Test
    void testFindOneIfEmpty() {
        Product foundProduct = productRepository.findOne("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductName("Sampo Cap Usep");
        newProduct.setProductQuantity(50);

        Product returnedProduct = productRepository.edit(product.getProductId(), newProduct);
        Product updatedProduct = productRepository.findOne(product.getProductId());

        assertNotNull(returnedProduct);
        assertEquals(updatedProduct.getProductName(), newProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), newProduct.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductName("Sampo Cap Usep");
        newProduct.setProductQuantity(50);

        Product updatedProduct = productRepository.findOne("aed0131d-ffdb-47bd-a7f6-424183c80188");
        Product returnedProduct = productRepository.edit("aed0131d-ffdb-47bd-a7f6-424183c80188", newProduct);

        assertNull(updatedProduct);
        assertNull(returnedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product returnedProduct = productRepository.delete(product.getProductId());
        Product deletedProduct = productRepository.findOne(product.getProductId());

        assertNull(deletedProduct);
        assertNotNull(returnedProduct);
        assertEquals(returnedProduct.getProductId(), product.getProductId());
        assertEquals(returnedProduct.getProductName(), product.getProductName());
        assertEquals(returnedProduct.getProductQuantity(), product.getProductQuantity());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonExistentProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.delete("aed0131d-ffdb-47bd-a7f6-424183c80188");
        assertNull(deletedProduct);
    }
}
