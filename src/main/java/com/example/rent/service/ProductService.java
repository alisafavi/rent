package com.example.rent.service;

import com.example.rent.Exception.BadRequest;
import com.example.rent.Exception.IdNotFoundException;
import com.example.rent.model.Category;
import com.example.rent.model.Product;
import com.example.rent.model.User;
import com.example.rent.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RentService rentService;
//-------------------

    public String addProduct(Product product){
            productRepository.save(product);
            return "Product added successfully";
    }

    public List<Product> myProduct(User user){
        return productRepository.findByUser(user);
    }

    //------------------
    public String updateProduct(Product product){
        if(!existProductById(product.getId())) throw  new IdNotFoundException();
        if(!userService.isExistsUser(product.getUser().getId())) throw new BadRequest();
        if(!categoryService.isExistsCategory(product.getCategory().getId())) throw new BadRequest();

        productRepository.save(product);
        return "Product is updateed successfully";
    }

    public String deleteProduct(int id){
        Optional<Product> product=productRepository.findById(id);
        if(!product.isPresent()) throw  new IdNotFoundException();

        if(!canDeleteProduct(product.get())) throw new BadRequest();

        productRepository.deleteById(id);
        return "Product is deleted successfully";
    }

    public boolean existProductById(int id){
        return productRepository.existsById(id);
    }

    public List<Product> getAllProductsByCategory(Category category){
        return   productRepository.findBycategory(category);
    }
    public Product findProductById(int id){
        Optional<Product> products= productRepository.findById(id);
        if(!products.isPresent()) throw new IdNotFoundException();
        return products.get();
    }
    public List<Product> getAllProductsByUser(User user){
        return productRepository.findByUser(user);
    }

    public List<Product> getAllProducts(){
        List<Product> productList = productRepository.findAll();
//        productList.stream().filter()


//        productList.sort(Comparator.comparing(Product::getName));
        return productList;
    }

    public boolean canDeleteProduct(Product product){
        return rentService.findByProduct(product)==null ? true : false;
    }
}
