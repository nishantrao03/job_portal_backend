package com.tpcportal.backend.repository;

import com.tpcportal.backend.entity.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class JobFetchRepositoryImpl implements JobFetchRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Job> findJobByEligibilityCriteria(int gradYear, String degree, String branch, BigDecimal cpi, Integer gender, String rollNo) {
        String queryString = "SELECT j FROM Job j " +
                "JOIN EligibilityCriteria e ON j.jobId = e.job.jobId " +
                "LEFT JOIN Application a ON j.jobId = a.job.jobId AND a.userData.rollNo = :rollNo " +
                "WHERE e.id.gradYear = :gradYear AND e.id.degree = :degree AND e.id.branch = :branch " +
                "AND (e.cpiCutoff IS NULL OR e.cpiCutoff <= :cpi) " +
                "AND (e.gender = 2 OR e.gender = :gender) " +
                "AND a.job.jobId IS NULL";

        TypedQuery<Job> query = entityManager.createQuery(queryString, Job.class);
        query.setParameter("gradYear", gradYear);
        query.setParameter("degree", degree);
        query.setParameter("branch", branch);
        query.setParameter("cpi", cpi);
        query.setParameter("gender", gender);
        query.setParameter("rollNo", rollNo);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    @Override
    public List<Job> findJobsByUserApplications(String rollNo) {
        String queryString = "SELECT j FROM Job j " +
                "JOIN Application a ON j.jobId = a.job.jobId " +
                "WHERE a.userData.rollNo = :rollNo";
        TypedQuery<Job> query = entityManager.createQuery(queryString, Job.class);
        query.setParameter("rollNo", rollNo);

        return query.getResultList();
    }

    @Override
    public List<Job> findQualifiedJobsByUser(String rollNo) {
        String queryString = "SELECT j FROM Job j " +
                "JOIN Application a ON j.jobId = a.job.jobId " +
                "WHERE a.userData.rollNo = :rollNo " +
                "AND a.selected = true";

        TypedQuery<Job> query = entityManager.createQuery(queryString, Job.class);
        query.setParameter("rollNo", rollNo);
        return query.getResultList();
    }

    @Override
    public List<Job> findJobsInProcess(String rollNo) {
        String queryString = "SELECT j FROM Job j WHERE j.userData.rollNo = :rollNo AND j.numberOfRounds <> j.completedRounds";
        TypedQuery query = entityManager.createQuery(queryString, Job.class);
        query.setParameter("rollNo", rollNo);
        return query.getResultList();
    }

    @Override
    public List<Job> findCompletedJobsForPhMember(String rollNo) {
        String queryString = "SELECT j FROM Job j WHERE j.userData.rollNo = :rollNo AND j.numberOfRounds = j.completedRounds";
        TypedQuery query = entityManager.createQuery(queryString, Job.class);
        query.setParameter("rollNo", rollNo);
        return query.getResultList();
    }

}
