package id.ac.ui.id.cs.adprog.eshop.repository;

import id.ac.ui.id.cs.adprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
