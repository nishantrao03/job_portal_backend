package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passwords")
public class UserCredentials {
    @Id
    @Column(name = "roll_no", nullable = false, unique = true)
    private String rollno;

    @Column(name = "insti_mail", unique = true)
    private String instiMail;

    @Column(name = "personal_mail", nullable = false)
    private String personalMail;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userCredentials", fetch = FetchType.LAZY)
    @JsonManagedReference
    private UserData userData;

    @OneToMany(mappedBy = "userCredentials", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Role> roles;

    public UserCredentials(){

    }

    public UserCredentials(String rollno, String password, String instiMail, String personalMail) {
        this.rollno = rollno;
        this.password = password;
        this.instiMail = instiMail;
        this.personalMail = personalMail;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    // Helper function to add Role
    public void addRole(Role role) {
        if(roles.size()==0){
            roles = new ArrayList<>();
        }
        roles.add(role);
        role.setUserCredentials(this);
    }
}
