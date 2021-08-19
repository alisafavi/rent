package com.example.rent.restController;


import com.example.rent.Exception.IdNotFoundException;
import com.example.rent.model.Category;
import com.example.rent.model.Product;
import com.example.rent.model.User;
import com.example.rent.repository.UserChangedRepository;
import com.example.rent.service.CategoryService;
import com.example.rent.service.ProductService;
import com.example.rent.service.UserChanngedService;
import com.example.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductRestController {
    @Resource
    private ProductService productService;
    @Resource
    private UserRestService userRestService;
    @Resource
    private CategoryService categoryService;

    //---------------------

    @Autowired
    UserChangedRepository userChangedRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserChanngedService userChanngedService;

    @GetMapping("/all")
    public void products(){
        List<Product> products = productService.getAllProducts();
        products.stream().forEach(e -> System.out.println(e.getId() + " \t " +e.getName()));
    }

    @GetMapping("/update/{id}")
    public String updateProducte(@PathVariable int id ){
//        model.addAttribute("product",new Product("apple","white",null,null));
        Product product = productService.findProductById(id);
        return product.getName() +" \t "+ product.getAttributes();
    }

    @PutMapping("/update/{id}")
    public String updateProducte(@PathVariable int id , @RequestParam Map<String,String> map ){
        Product product = new Product();
        product.setId(id);
        product.setName(map.get("name"));

        productService.addProduct(product);
        userChanngedService.addRecord(product,"update product");

        return "successful";
    }


    //---------------------


    @GetMapping(value = "/search/{id}")
    public Product search(@PathVariable int id) {
       return productService.findProductById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/createdBySpecificUsers/{id_user}")
    public List<Product> getAllProductsCreatedBySpecificUsers(@PathVariable int id_user) {
       Optional<User> user= userRestService.findUserById(id_user);
       if(!user.isPresent()) throw  new IdNotFoundException();
       return productService.getAllProductsByUser(user.get());
    }

    @GetMapping(value = "/inSpecificCategory/{id_category}")
    public List<Product> inSpecificCategory(@PathVariable int id_category) {
        Optional<Category> categoriy=categoryService.findCategoryById(id_category);

        if(!categoriy.isPresent()) throw new IdNotFoundException();
        return productService.getAllProductsByCategory(categoriy.get());
    }

    @PostMapping("/add")
    public String createCategory(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);

    }
}
