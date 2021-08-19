package com.example.rent.service;


import com.example.rent.model.Product;
import com.example.rent.model.Rent;
import com.example.rent.model.User;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public Optional<Rent> findById(int id){
        return rentRepository.findById(id);
    }
    public void save(Rent rent){
        rentRepository.save(rent);
    }
    public void delete(int rentId){
        rentRepository.deleteById(rentId);
    }
//    public boolean IsProductRental(Products products){
//        return rentRepository.findByProduct(products).isEmpty();
//    }
    public Rent AddRentDetails(Rent rent){
        rent.setDateStart(Timestamp.valueOf(LocalDateTime.now()));
        rent.setDateFinish(Timestamp.valueOf(LocalDateTime.now().plus(444, ChronoUnit.MINUTES)));
       return rentRepository.save(rent);
    }

    public Rent findByProduct(Product product){
        return rentRepository.findByProduct(product);
    }

//        public void obsoleteRent(int rentId){
//        rentRepository.obsoleteRent(rentId);
//    }

    public List<Rent> myRent(User user){
        checkRentedDate();
        List<Rent> rents = rentRepository.findByUser(user);
        return rents;
    }

    public void checkRentedDate(){
        rentRepository.findAll().stream().forEach(rent -> {
           if (rent.getDateFinish().before(Timestamp.valueOf(LocalDateTime.now()))) {
               delete(rent.getId());
           }
        });

    }
}
