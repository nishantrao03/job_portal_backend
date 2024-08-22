package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpcportal.backend.embeddedids.ApplicationId;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @MapsId("rollNo")
    @JoinColumn(name = "roll_no")
    @JsonBackReference
    private UserData userData;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;

    @Column(name = "personal_mail", nullable = false)
    private String personalMail;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private BigDecimal cpi;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false)
    private String linkedin;

    @Column(name = "rounds_cleared", nullable = true)
    private Integer roundsCleared = 0;

    @Column(name = "selected", nullable = true)
    private Boolean selected = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "cover_letter", nullable = true)
    private String coverLetter;

    public Application(){

    }

    public ApplicationId getId() {
        return id;
    }

    public void setId(ApplicationId id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Integer getRoundsCleared() {
        return roundsCleared;
    }

    public void setRoundsCleared(Integer roundsCleared) {
        this.roundsCleared = roundsCleared;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

//    @Override
//    public String toString() {
//        return "Application{" +
//                "id=" + id +
//                ", userData=" + userData +
//                ", job=" + job +
//                ", personalMail='" + personalMail + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", cpi=" + cpi +
//                ", resume='" + resume + '\'' +
//                ", linkedin='" + linkedin + '\'' +
//                ", roundsCleared=" + roundsCleared +
//                ", selected=" + selected +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                ", coverLetter='" + coverLetter + '\'' +
//                '}';
//    }
}
