package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.JobDTO;
import com.tpcportal.backend.entity.Job;

import java.math.BigDecimal;
import java.util.List;

public interface JobService {
    List<Job> findJobByEligibilityCriteria(int gradYear, String degree, String branch, BigDecimal cpi, Integer gender, String rollNo);
    List<Job> findJobsByUserApplications(String rollNo);
    List<Job> findQualifiedJobsByUser(String rollNo);
    boolean addJob(JobDTO jobDTO);
    boolean updateJob(JobDTO jobDTO);
    List<Job> findJobsInProcess(String rollNo);
    List<Job> findCompletedJobs(String rollNo);
}
