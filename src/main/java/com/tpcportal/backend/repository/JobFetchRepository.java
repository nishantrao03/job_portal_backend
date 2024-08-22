package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.Job;

import java.math.BigDecimal;
import java.util.List;

public interface JobFetchRepository {
    List<Job> findJobByEligibilityCriteria(int gradYear, String degree, String branch, BigDecimal cpi, Integer gender, String rollNo);
    List<Job> findJobsByUserApplications(String rollNo);
    List<Job> findQualifiedJobsByUser(String rollNo);
    List<Job> findJobsInProcess(String rollNo);
    List<Job> findCompletedJobsForPhMember(String rollNo);
}
