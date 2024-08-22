package com.tpcportal.backend.dto;

public class StudentDetailsDTO {
    private String rollNo;
    private String instiMail;
    private String firstName;
    private String middleName;
    private String lastName;

    public StudentDetailsDTO(){

    }

    public StudentDetailsDTO(String rollNo, String instiMail, String firstName, String middleName, String lastName) {
        this.rollNo = rollNo;
        this.instiMail = instiMail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getInstiMail() {
        return instiMail;
    }

    public void setInstiMail(String instiMail) {
        this.instiMail = instiMail;
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
}
