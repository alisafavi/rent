package com.example.rent.repository;

import com.example.rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUserName(String userName);
    boolean existsByUserName(String Username);
    boolean existsByPassword(String password);
}
