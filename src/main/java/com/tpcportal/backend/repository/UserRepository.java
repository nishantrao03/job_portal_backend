package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCredentials,String> {
    UserCredentials findByRollno(String rollno);
}
