package com.example.rent.service;

import com.example.rent.model.Product;
import com.example.rent.model.UserWorkInfo;
import com.example.rent.repository.UserChangedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserChanngedService {

    @Autowired
    UserChangedRepository userChangedRepository;
    @Autowired
    UserService userService;


    public void addRecord(Product product , String strJob){

        UserWorkInfo userWorkInfo = new UserWorkInfo();
        userWorkInfo.setUser(userService.getAuthenticateUser());
        userWorkInfo.setProduct(product);
        userWorkInfo.setJob(strJob);
        userWorkInfo.setDate(Timestamp.valueOf(LocalDateTime.now()));

    }
}
