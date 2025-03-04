package id.ac.ui.id.cs.adprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("72d93c3c-73ce-44b1-81d1-e218139dfe8e");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("671b7b4a-dd81-4828-8305-90e58f20da32");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sampo Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order("0c0b4cc7-f42b-4170-bd5b-8c18b0d130c9", this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("MEOW");
        });
    }
}
