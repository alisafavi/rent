package com.example.rent.service;

import com.example.rent.model.MyUserDetail;
import com.example.rent.model.User;
import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user){
        userRepository.findUserByUserName(user.getUserName()).ifPresent(e -> {
            throw new IllegalArgumentException("User with that name already exists!");
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(userName);
        return user.map(MyUserDetail::new).get();
    }

    public User findUserByUsername(String username){
        Optional<User> user = userRepository.findUserByUserName(username);
        return user.get();
    }

    public boolean isExistsUser(int id){
        return userRepository.existsById(id);
    }

    public User getAuthenticateUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() == "anonymousUser"){
            return null;
        }
        MyUserDetail userPrincipal = (MyUserDetail) authentication.getPrincipal();
        User user = findUserByUsername(userPrincipal.getUsername());
        return user;
    }

}
