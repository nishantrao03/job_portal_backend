package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, String > {
    UserData findByRollNo(String rollNo);
}
