package com.tpcportal.backend.embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecruitmentRoundId implements Serializable {
    @Column(name = "job_id")
    private String jobId;

    @Column(name = "round_number")
    private int roundNumber;

    public RecruitmentRoundId() {
    }

    public RecruitmentRoundId(String jobId, int roundNumber) {
        this.jobId = jobId;
        this.roundNumber = roundNumber;
    }

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentRoundId that = (RecruitmentRoundId) o;
        return roundNumber == that.roundNumber && Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, roundNumber);
    }
}
