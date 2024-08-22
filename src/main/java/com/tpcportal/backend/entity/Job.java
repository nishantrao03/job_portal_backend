package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tpcportal.backend.embeddedids.EligibilityCriteriaId;
import com.tpcportal.backend.embeddedids.RecruitmentRoundId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_id", nullable = false)
    private String jobId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_details")
    private String companyDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false)
    private JobType jobType;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "package")
    private BigDecimal pkg;

    @Column(name = "stipend")
    private BigDecimal stipend;

    @Column(name = "location")
    private String location;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "completed_rounds", nullable = false)
    private Integer completedRounds = 0;

    @Column(name = "ph_member_name", nullable = false)
    private String phMemberName;

    @Column(name = "phone_number_stud", nullable = false)
    private String phoneNumberStud;

    @Column(name = "insti_mail", nullable = false)
    private String instiMail;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ph_roll_no", referencedColumnName = "roll_no")
    @JsonBackReference
    private UserData userData;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<EligibilityCriteria> eligibilityCriterias = new ArrayList<>();
//
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<RecruitmentRound> recruitmentRounds = new ArrayList<>();

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Application> applications = new ArrayList<>();

    @Column(name = "number_of_rounds", nullable = false)
    private Integer numberOfRounds = recruitmentRounds.size();

    public Job(){

    }

    public Job(String jobId, String companyName, String companyDetails, JobType jobType, String role, BigDecimal pkg, BigDecimal stipend, String location, Integer duration, LocalDate deadline, String phMemberName, String phoneNumberStud, String instiMail, String jobDescription, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.jobId = jobId;
        this.companyName = companyName;
        this.companyDetails = companyDetails;
        this.jobType = jobType;
        this.role = role;
        this.pkg = pkg;
        this.stipend = stipend;
        this.location = location;
        this.duration = duration;
        this.deadline = deadline;
        this.phMemberName = phMemberName;
        this.phoneNumberStud = phoneNumberStud;
        this.instiMail = instiMail;
        this.jobDescription = jobDescription;
        setEligibilityCriterias(eligibilityCriterias);
        setRecruitmentRounds(recruitmentRounds);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Enum for job_type
    public enum JobType {
        j, i
    }

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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
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

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(Integer numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public Integer getCompletedRounds() {
        return completedRounds;
    }

    public void setCompletedRounds(Integer completedRounds) {
        this.completedRounds = completedRounds;
    }

    public String getPhMemberName() {
        return phMemberName;
    }

    public void setPhMemberName(String phMemberName) {
        this.phMemberName = phMemberName;
    }

    public String getPhoneNumberStud() {
        return phoneNumberStud;
    }

    public void setPhoneNumberStud(String phoneNumberStud) {
        this.phoneNumberStud = phoneNumberStud;
    }

    public String getInstiMail() {
        return instiMail;
    }

    public void setInstiMail(String instiMail) {
        this.instiMail = instiMail;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<EligibilityCriteria> getEligibilityCriterias() {
        return eligibilityCriterias;
    }

    public void setEligibilityCriterias(List<EligibilityCriteria> eligibilityCriterias) {
        this.eligibilityCriterias = eligibilityCriterias;
    }

    public List<RecruitmentRound> getRecruitmentRounds() {
        return recruitmentRounds;
    }

    public void setRecruitmentRounds(List<RecruitmentRound> recruitmentRounds) {
        this.recruitmentRounds = recruitmentRounds;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void addApplication(Application application) {
        applications.add(application);
        application.setJob(this);
    }

    public void addEligibilityCriterias(EligibilityCriteria eligibilityCriteria){
        eligibilityCriterias.add(eligibilityCriteria);
        eligibilityCriteria.setJob(this);
    }

    public void addRecruitmentRounds(RecruitmentRound recruitmentRound){
        recruitmentRounds.add(recruitmentRound);
        recruitmentRound.setJob(this);
    }

//    public void setEligibilityJob(List<EligibilityCriteria> eligibilityCriterias){
//        for(EligibilityCriteria eligibilityCriteria : eligibilityCriterias){
//            eligibilityCriteria.setJob(this);
//        }
//    }
//
//    public void setRecruitmentJob(List<RecruitmentRound> recruitmentRounds){
//        for(RecruitmentRound recruitmentRound : recruitmentRounds){
//            recruitmentRound.setJob(this);
//        }
//    }

    @PrePersist
    public void prePersist() {
        this.numberOfRounds = recruitmentRounds.size();
    }

    @PreUpdate
    public void preUpdate() {
        this.numberOfRounds = recruitmentRounds.size();
    }

    public void clearEligibilityCriterias(){
        if(this.eligibilityCriterias!=null){
            for(EligibilityCriteria eligibilityCriteria : this.eligibilityCriterias){
                eligibilityCriteria.setJob(null);
            }
            this.eligibilityCriterias.clear();
        }
    }

    public void clearRecruitmentRounds(){
        if(this.recruitmentRounds!=null){
            for (RecruitmentRound recruitmentRound : this.recruitmentRounds){
                recruitmentRound.setJob(null);
            }
            this.recruitmentRounds.clear();
        }
    }
}
