package com.tpcportal.backend.dto;

import java.util.List;

public class UpdateRoundsClearedDTO {
    private String rollNo;
    private List<String> rollNos;
    private String jobId;

    // Getters and Setters


    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public List<String> getRollNos() {
        return rollNos;
    }

    public void setRollNos(List<String> rollNos) {
        this.rollNos = rollNos;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
