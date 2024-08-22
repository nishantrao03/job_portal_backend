package com.tpcportal.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "verificationqueue")
public class Alumni {

    @Id
    @Column(name = "roll_no", nullable = false, length = 20)
    private String rollNo;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "degree", nullable = false, length = 50)
    private String degree;

    @Column(name = "branch", nullable = false, length = 50)
    private String branch;

    @Column(name = "cpi", precision = 3, scale = 2)
    private BigDecimal cpi;

    @Column(name = "resume", length = 255)
    private String resume;

    @Column(name = "linkedin", length = 255)
    private String linkedin;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "personal_mail", nullable = false, unique = true, length = 255)
    private String personalMail;

    public Alumni(){

    }

    public Alumni(String rollNo, String firstName, String middleName, String lastName, String degree, String branch, BigDecimal cpi, String resume, String linkedin, LocalDate dateOfBirth, String phoneNumber, String password, String personalMail) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.degree = degree;
        this.branch = branch;
        this.cpi = cpi;
        this.resume = resume;
        this.linkedin = linkedin;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.personalMail = personalMail;
    }

    // Getters and Setters
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }
}
