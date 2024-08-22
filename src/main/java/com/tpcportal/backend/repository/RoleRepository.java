package com.tpcportal.backend.repository;

import com.tpcportal.backend.embeddedids.RoleId;
import com.tpcportal.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, RoleId> {
    //List<Role> findByRollNo(String rollNo);

}
