package com.example.rent.repository;

import com.example.rent.model.Category;
import com.example.rent.model.Product;
import com.example.rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findBycategory(Category category);
    List<Product> findByUser(User user);
    List<Product> findAll();
}