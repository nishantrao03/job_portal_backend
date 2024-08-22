package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_details")
public class UserData {
    @Id
    @Column(name = "roll_no", nullable = false, unique = true)
    private String rollNo;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dob;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "cpi", nullable = false)
    private BigDecimal cpi;

    @Column(name = "resume")
    private String resume;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "10th_percentage", nullable = false)
    private BigDecimal tenthPercentage;

    @Column(name = "12th_or_diploma_percentage", nullable = false)
    private BigDecimal twelfthOrDiplomaPercentage;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "roll_no", referencedColumnName = "roll_no")
    @JsonBackReference
    private UserCredentials userCredentials;

    @OneToMany(mappedBy = "userData", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Job> jobs = new ArrayList<>();

    public UserData(){

    }

    public UserData(String rollNo, String firstName, String middleName, LocalDate dob, String lastName, String degree, String branch, BigDecimal cpi, String resume, String linkedin, String phoneNumber, Integer gender, BigDecimal tenthPercentage, BigDecimal twelfthOrDiplomaPercentage) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dob = dob;
        this.lastName = lastName;
        this.degree = degree;
        this.branch = branch;
        this.cpi = cpi;
        this.resume = resume;
        this.linkedin = linkedin;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.tenthPercentage = tenthPercentage;
        this.twelfthOrDiplomaPercentage = twelfthOrDiplomaPercentage;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public BigDecimal getCpi() {
        return cpi;
    }

    public void setCpi(BigDecimal cpi) {
        this.cpi = cpi;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public BigDecimal getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(BigDecimal tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }

    public BigDecimal getTwelfthOrDiplomaPercentage() {
        return twelfthOrDiplomaPercentage;
    }

    public void setTwelfthOrDiplomaPercentage(BigDecimal twelfthOrDiplomaPercentage) {
        this.twelfthOrDiplomaPercentage = twelfthOrDiplomaPercentage;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addApplication(Application application) {
        applications.add(application);
        application.setUserData(this);
    }

    public void addJob(Job job){
        jobs.add(job);
        job.setUserData(this);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "rollNo='" + rollNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", degree='" + degree + '\'' +
                ", branch='" + branch + '\'' +
                ", cpi=" + cpi +
                ", resume='" + resume + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", tenthPercentage=" + tenthPercentage +
                ", twelfthOrDiplomaPercentage=" + twelfthOrDiplomaPercentage +
                ", applications=" + applications +
                '}';
    }
}
