package com.tpcportal.backend.dto;

import com.tpcportal.backend.entity.EligibilityCriteria;
import com.tpcportal.backend.entity.Job;
import com.tpcportal.backend.entity.RecruitmentRound;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobDTO {
    private String jobId;
    private String companyName;
    private String companyDetails;
    private Job.JobType jobType;
    private String role;
    private BigDecimal pkg;
    private BigDecimal stipend;
    private String location;
    private Integer duration;
    private LocalDate deadline;
    private String phMemberName;
    private String instiMail;
    private String phoneNumberStud;
    private String jobDescription;
    private String phRollNo;
    private List<EligibilityCriteriaDTO> eligibilityCriteria;
    private List<RecruitmentRoundDTO> recruitmentRounds;

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(String companyDetails) {
        this.companyDetails = companyDetails;
    }

    public Job.JobType getJobType() {
        return jobType;
    }

    public void setJobType(Job.JobType jobType) {
        this.jobType = jobType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getPkg() {
        return pkg;
    }

    public void setPkg(BigDecimal pkg) {
        this.pkg = pkg;
    }

    public BigDecimal getStipend() {
        return stipend;
    }

    public void setStipend(BigDecimal stipend) {
        this.stipend = stipend;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getPhMemberName() {
        return phMemberName;
    }

    public void setPhMemberName(String phMemberName) {
        this.phMemberName = phMemberName;
    }

    public String getInstiMail() {
        return instiMail;
    }

    public void setInstiMail(String instiMail) {
        this.instiMail = instiMail;
    }

    public String getPhoneNumberStud() {
        return phoneNumberStud;
    }

    public void setPhoneNumberStud(String phoneNumberStud) {
        this.phoneNumberStud = phoneNumberStud;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<EligibilityCriteriaDTO> getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(List<EligibilityCriteriaDTO> eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public List<RecruitmentRoundDTO> getRecruitmentRounds() {
        return recruitmentRounds;
    }

    public void setRecruitmentRounds(List<RecruitmentRoundDTO> recruitmentRounds) {
        this.recruitmentRounds = recruitmentRounds;
    }

    public String getPhRollNo() {
        return phRollNo;
    }

    public void setPhRollNo(String phRollNo) {
        this.phRollNo = phRollNo;
    }

    public List<EligibilityCriteria> createEligibilityCriterias(){
        List<EligibilityCriteria> eligibilityCriterias = new ArrayList<>();
        for(EligibilityCriteriaDTO eligibilityCriteriaDTO : eligibilityCriteria){
            eligibilityCriterias.add(eligibilityCriteriaDTO.createElgibilityCriteria());
        }
        return eligibilityCriterias;
    }

    public List<RecruitmentRound> createRecruitmentRounds(){
        List<RecruitmentRound> recruitmentRounds1 = new ArrayList<>();
        for (RecruitmentRoundDTO recruitmentRoundDTO : recruitmentRounds){
            recruitmentRounds1.add(recruitmentRoundDTO.createRecruitmentRound());
        }
        return recruitmentRounds1;
    }
}
