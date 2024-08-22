package com.tpcportal.backend.dto;

import java.math.BigDecimal;

public class ApplicationDTO {
    private String rollNo;
    private String personalMail;
    private String phoneNumber;
    private BigDecimal cpi;
    private String resume;
    private String linkedIn;
    private String coverLetter;
    private String jobId;

    public ApplicationDTO(){

    }

    public ApplicationDTO(String rollNo, String personalMail, String phoneNumber, BigDecimal cpi, String resume, String linkedIn, String coverLetter, String jobId) {
        this.rollNo = rollNo;
        this.personalMail = personalMail;
        this.phoneNumber = phoneNumber;
        this.cpi = cpi;
        this.resume = resume;
        this.linkedIn = linkedIn;
        this.coverLetter = coverLetter;
        this.jobId = jobId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }

    public BigDecimal getCpi() {
        return cpi;
    }

    public void setCpi(BigDecimal cpi) {
        this.cpi = cpi;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
