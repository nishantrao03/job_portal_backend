package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpcportal.backend.embeddedids.RoleId;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @EmbeddedId
    private RoleId id;

//    @Column(name = "roll_no", nullable = false)
//    private String rollNo;
//
//    @Column(name = "role", nullable = false)
//    private String role;

    @MapsId("rollNo")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "roll_no", referencedColumnName = "roll_no")
    @JsonBackReference
    UserCredentials userCredentials;

    public Role() {
    }

    public Role(String rollNo, String role) {
        this.id = new RoleId(rollNo, role);
    }

    // Getters and Setters

    public RoleId getId() {
        return id;
    }

    public void setId(RoleId id) {
        this.id = id;
    }

    public String getRollNo() {
        return id.getRollNo();
    }

    public void setRollNo(String rollNo) {
        id.setRollNo(rollNo);
    }

    public String getRole() {
        return id.getRole();
    }

    public void setRole(String role) {
        id.setRole(role);
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rollNo='" + id.getRollNo() + '\'' +
                ", role='" + id.getRole() + '\'' +
                '}';
    }
}
