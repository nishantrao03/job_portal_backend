package com.tpcportal.backend.model;

public class JwtRequest {
    private String rollno;
    private String password;

    public JwtRequest() {}

    public JwtRequest(String rollno, String password) {
        this.rollno = rollno;
        this.password = password;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtRequest{" +
                "rollno='" + rollno + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
