package com.tpcportal.backend.embeddedids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationId implements Serializable {
    private String rollNo;
    private String jobId;

    public ApplicationId() {
    }

    public ApplicationId(String rollNo, String jobId) {
        this.rollNo = rollNo;
        this.jobId = jobId;
    }

    // Getters and Setters
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationId that = (ApplicationId) o;
        return Objects.equals(rollNo, that.rollNo) && Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, jobId);
    }
}
