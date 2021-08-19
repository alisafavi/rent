package com.example.rent.restController;


import com.example.rent.Exception.BadRequest;
import com.example.rent.Exception.IdNotFoundException;
import com.example.rent.model.Product;
import com.example.rent.model.User;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public String addUser(User user){
        if(!checkValid(user)){
            return "invalid userName or password";
        }else{
            userRepository.save(user);
            return "user is added successfully";
        }
    }

    public String deleteUser(int id){
        Optional<User> users=userRepository.findById(id);
        if(!users.isPresent()) throw  new IdNotFoundException();

        if(!canDeleteUser(users.get())) throw new BadRequest();

         userRepository.deleteById(id);
         return "User is deleted successfully";
    }

    public String updateUser(User user) {
        int id = user.getId();
        Optional<User> users = findUserById(id);///categoryRepository.findById(id);
        if (users.isPresent() && checkValid(user)) {
//            users.get().setName(user.getName());
//            users.get().setUserName(user.getUserName());
//            users.get().setPassword(user.getPassword());
            userRepository.save(user);
            return "user is updated successfully";
        } else {
            return "This user does not exist or invalid userName or password";
        }
    }
    public boolean checkValid(User user){
        if(user == null) return true;
        return !userRepository.existsByUserName(user.getUserName()) && !userRepository.existsByPassword(user.getPassword());
    }
    public boolean canDeleteUser(User user){
        List<Product> productList =productService.getAllProductsByUser(user);
        for (int i = 0; i < productList.size(); i++) {
           if(!productService.canDeleteProduct(productList.get(i))) {
               return false;
           }
        }
        deleteAllProducts(productList);
        return true;
    }
    public void deleteAllProducts( List<Product> productList){
        for (int i = 0; i < productList.size(); i++) {
        productService.deleteProduct(productList.get(i).getId());
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> findUserById(int id){
        return userRepository.findById(id);
    }
    public boolean isExistsUser(int id){
        return userRepository.existsById(id);
    }
    public Optional<User> findUserByName(String name){
        return userRepository.findUserByUserName(name);
    }
}
