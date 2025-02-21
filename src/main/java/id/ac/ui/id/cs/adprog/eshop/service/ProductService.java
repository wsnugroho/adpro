package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product delete(String id);

    List<Product> findAll();

    Product findOne(String id);

    Product edit(String id, Product newProduct);
}
