package id.ac.ui.id.cs.adprog.eshop.controller;

import id.ac.ui.id.cs.adprog.eshop.model.Car;
import id.ac.ui.id.cs.adprog.eshop.model.Product;
import id.ac.ui.id.cs.adprog.eshop.service.CarServiceImpl;
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

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    public CarController(ProductService service) {
        super(service);
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "car/listCar";
    }

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "car/createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:/car/listCar";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "car/editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.updateById(car.getCarId(), car);
        return "redirect:/car/listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam String carId, Model model) {
        carService.deleteById(carId);
        return "redirect:/car/listCar";
    }
}