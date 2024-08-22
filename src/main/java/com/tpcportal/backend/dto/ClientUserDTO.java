package com.tpcportal.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientUserDTO {
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
    private BigDecimal twelfthOrDiplomaPercentage;
    private BigDecimal cpi; //y
    private String resume; //y
    private String linkedin; //y
    private String phoneNumber; //y
    private boolean isStudent;
    private boolean isPh_member;
    private boolean isAdmin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public ClientUserDTO(){

    }

    public ClientUserDTO(String rollNo, String middleName, String firstName, String lastName, LocalDate dob, String instiMail, String personalMail, String degree, String branch, BigDecimal cpi, String resume, String linkedin, String phoneNumber, boolean isStudent, boolean isPh_member, boolean isAdmin, String token, Integer gender, BigDecimal tenthPercentage, BigDecimal twelfthOrDiplomaPercentage) {
        this.rollNo = rollNo;
        this.middleName = middleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.instiMail = instiMail;
        this.personalMail = personalMail;
        this.degree = degree;
        this.branch = branch;
        this.cpi = cpi;
        this.resume = resume;
        this.linkedin = linkedin;
        this.phoneNumber = phoneNumber;
        this.isStudent = isStudent;
        this.isPh_member = isPh_member;
        this.isAdmin = isAdmin;
        this.token = token;
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

    public String getInstiMail() {
        return instiMail;
    }

    public void setInstiMail(String instiMail) {
        this.instiMail = instiMail;
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

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public boolean isPh_member() {
        return isPh_member;
    }

    public void setPh_member(boolean ph_member) {
        isPh_member = ph_member;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
        return "ClientUserDTO{" +
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
                ", isStudent=" + isStudent +
                ", isPh_member=" + isPh_member +
                ", isAdmin=" + isAdmin +
                ", token='" + token + '\'' +
                '}';
    }
}
