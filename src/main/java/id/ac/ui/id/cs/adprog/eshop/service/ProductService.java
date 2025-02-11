package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product findOne(String id);
    public Product edit(String id, Product newProduct);
}
