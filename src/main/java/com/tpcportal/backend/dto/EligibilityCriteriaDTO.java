package com.tpcportal.backend.dto;

import com.tpcportal.backend.embeddedids.EligibilityCriteriaId;
import com.tpcportal.backend.entity.EligibilityCriteria;

import java.math.BigDecimal;

public class EligibilityCriteriaDTO {
    private String jobId;
    private int gradYear;
    private String degree;
    private String branch;
    private Integer gender;
    private BigDecimal cpiCutoff;

    public EligibilityCriteriaDTO(){

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public BigDecimal getCpiCutoff() {
        return cpiCutoff;
    }

    public void setCpiCutoff(BigDecimal cpiCutoff) {
        this.cpiCutoff = cpiCutoff;
    }

    public EligibilityCriteria createElgibilityCriteria(){
        EligibilityCriteriaId eligibilityCriteriaId = new EligibilityCriteriaId(this.jobId, this.gradYear, this.degree, this.branch);
        EligibilityCriteria eligibilityCriteria = new EligibilityCriteria(eligibilityCriteriaId, this.gender, this.cpiCutoff);
        return eligibilityCriteria;
    }
}
