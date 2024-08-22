package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpcportal.backend.embeddedids.RecruitmentRoundId;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recruitment_rounds")
public class RecruitmentRound {

    @EmbeddedId
    private RecruitmentRoundId id;

    @Column(name = "round_type", nullable = false)
    private String roundType;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "duration")
    private Integer duration; // Use Integer to allow NULL values

    @Column(name = "description")
    private String description;

    @Column(name = "link_or_venue")
    private String linkOrVenue;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;

    public RecruitmentRound(){

    }

    public RecruitmentRound(RecruitmentRoundId id, String roundType, Date date, Time time, Integer duration, String description, String linkOrVenue) {
        this.id = id;
        this.roundType = roundType;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.description = description;
        this.linkOrVenue = linkOrVenue;
    }

    // Getters and Setters

    public RecruitmentRoundId getId() {
        return id;
    }

    public void setId(RecruitmentRoundId id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkOrVenue() {
        return linkOrVenue;
    }

    public void setLinkOrVenue(String linkOrVenue) {
        this.linkOrVenue = linkOrVenue;
    }
}
