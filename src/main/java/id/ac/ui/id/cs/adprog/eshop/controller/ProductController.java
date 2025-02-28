package id.ac.ui.id.cs.adprog.eshop.controller;

import id.ac.ui.id.cs.adprog.eshop.model.Product;
import id.ac.ui.id.cs.adprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(Model model, @ModelAttribute Product product) {
        service.create(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable String id) {
        Product product = service.findOne(id);
        model.addAttribute("product", product);
        return "product/editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPut(Model model, @PathVariable String id, @ModelAttribute Product newProduct) {
        service.edit(id, newProduct);
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/delete/{id}")
    public String deleteProductPost(Model model, @PathVariable String id) {
        service.delete(id);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "product/productList";
    }
}