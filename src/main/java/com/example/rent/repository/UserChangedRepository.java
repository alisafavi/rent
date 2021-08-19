package com.example.rent.repository;

import com.example.rent.model.UserWorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChangedRepository extends JpaRepository<UserWorkInfo,Long> {
}
