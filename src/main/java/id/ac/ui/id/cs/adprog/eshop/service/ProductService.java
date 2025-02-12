package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product delete(String id);
    public List<Product> findAll();
}
