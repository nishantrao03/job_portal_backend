package com.tpcportal.backend.embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EligibilityCriteriaId implements Serializable {
    @Column(name = "job_id")
    private String jobId;

    @Column(name = "grad_year")
    private int gradYear;

    @Column(name = "degree")
    private String degree;

    @Column(name = "branch")
    private String branch;

    // Constructors, getters, setters, hashCode, and equals methods

    public EligibilityCriteriaId() {
    }

    public EligibilityCriteriaId(String jobId, int gradYear, String degree, String branch) {
        this.jobId = jobId;
        this.gradYear = gradYear;
        this.degree = degree;
        this.branch = branch;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityCriteriaId that = (EligibilityCriteriaId) o;
        return gradYear == that.gradYear && Objects.equals(jobId, that.jobId) &&
                Objects.equals(degree, that.degree) && Objects.equals(branch, that.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, gradYear, degree, branch);
    }
}
