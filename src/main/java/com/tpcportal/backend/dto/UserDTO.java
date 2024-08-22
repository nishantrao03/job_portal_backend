package com.tpcportal.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserDTO {
    private String rollNo; //y
    private String firstName; //y
    private String middleName; //y
    private String lastName; //y
    private LocalDate dob; //y
    private Integer gender; //y
    private String instiMail; //y
    private String personalMail; //y
    private String degree; //y
    private String branch; //y
    private BigDecimal tenthPercentage; //y
    private BigDecimal twelfthOrDiplomaPercentage; //y
    private BigDecimal cpi; //y
    private String resume; //y
    private String linkedin; //y
    private String phoneNumber; //y
    private String password; //n
    private String code; //n
//    private boolean isStudent;
//    private boolean isPh_member;
//    private boolean isAdmin;

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

    public String getInstiMail() {
        return instiMail;
    }

    public void setInstiMail(String instiMail) {
        this.instiMail = instiMail;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "rollNo='" + rollNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", instiMail='" + instiMail + '\'' +
                ", personalMail='" + personalMail + '\'' +
                ", degree='" + degree + '\'' +
                ", branch='" + branch + '\'' +
                ", tenthPercentage=" + tenthPercentage +
                ", twelfthOrDiplomaPercentage=" + twelfthOrDiplomaPercentage +
                ", cpi=" + cpi +
                ", resume='" + resume + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
