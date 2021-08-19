package com.example.rent.repository;

import com.example.rent.model.Product;
import com.example.rent.model.Rent;
import com.example.rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {
    Rent findByProduct(Product product);
    List<Rent> findByUser(User user);
}
