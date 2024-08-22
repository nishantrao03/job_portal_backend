package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniRepository extends JpaRepository<Alumni,String> {
}
