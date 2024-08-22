package com.tpcportal.backend.embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleId implements Serializable {
    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "role")
    private String role;

    public RoleId() {
    }

    public RoleId(String rollNo, String role) {
        this.rollNo = rollNo;
        this.role = role;
    }

    // Getters and Setters

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Override equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleId roleId = (RoleId) o;
        return Objects.equals(rollNo, roleId.rollNo) &&
                Objects.equals(role, roleId.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, role);
    }
}
