package com.example.rent.controller;

import com.example.rent.model.Product;
import com.example.rent.service.CategoryService;
import com.example.rent.service.ProductService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RentService rentService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("product",new Product());
        return "/product/add_product";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam Map<String,String> map,
                              @ModelAttribute Product product){

        map.remove("name",product.getName());
        JSONObject jsObject = new JSONObject(map);


//        String aa = jsObject.toString();
//        JSONObject ee = new JSONObject(aa);
//        Map<String, Object> oo = ee.toMap();

        product.setUser(userService.getAuthenticateUser());
        product.setAttributes(jsObject.toString());

        productService.addProduct(product);
        myProduct(new ModelMap());
        return "/pages/my_products";
    }

    @GetMapping("/delete/{productId}")
    public RedirectView deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new RedirectView("../../product/my_product");
    }
    @GetMapping("/all")
    public String all(ModelMap modelMap){
        List<Product> products = productService.getAllProducts().stream()
                .filter(product -> rentService.findByProduct(product)==null).collect(Collectors.toList());
        modelMap.addAttribute("products",products);
        return "pages/products";
    }

    @GetMapping("/update/{id}")
    public String updateProducte(@PathVariable int id , Model model){
        model.addAttribute("product",new Product("apple","white",null,null));
        return "product/update_product";
    }

    @PostMapping("/update")
    public String updateProducte(@RequestParam Map<String,String> map ,@RequestBody Product product){
        productService.addProduct(product);
        return "/profile";
    }

    @GetMapping("/my_product")
    public String myProduct(ModelMap modelMap){
        modelMap.addAttribute("myProducts",productService.getAllProductsByUser(userService.getAuthenticateUser()));
        return "pages/my_products";
    }

}
