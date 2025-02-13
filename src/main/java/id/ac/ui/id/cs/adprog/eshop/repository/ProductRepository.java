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
  
    public Product findOne(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product edit(String id, Product product) {
        for (Product currProduct : productData) {
            if (currProduct.getProductId().equals(id)) {
                currProduct.setProductName(product.getProductName());
                currProduct.setProductQuantity(product.getProductQuantity());
                return product;
            }
        }
        return null;
    }
  
    public Product delete(String id) {
        for (Product currProduct : productData) {
            if (currProduct.getProductId().equals(id)) {
                productData.remove(currProduct);
                return currProduct;
            }
        }
        return null;
    }
}
