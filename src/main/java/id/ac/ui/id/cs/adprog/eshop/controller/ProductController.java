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
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(Model model, @ModelAttribute Product product) {
        service.create(product);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductPost(Model model, @PathVariable String id) {
        service.delete(id);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "product/productList";
    }
}
