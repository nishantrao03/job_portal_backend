package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
    Job findByJobId(String jobId);
}
