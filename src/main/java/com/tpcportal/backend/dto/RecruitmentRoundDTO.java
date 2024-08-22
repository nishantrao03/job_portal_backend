package com.tpcportal.backend.dto;

import com.tpcportal.backend.embeddedids.RecruitmentRoundId;
import com.tpcportal.backend.entity.RecruitmentRound;

import java.sql.Date;
import java.sql.Time;

public class RecruitmentRoundDTO {
    private String jobId;
    private int roundNumber;
    private String roundType;
    private Date date;
    private Time time;
    private Integer duration; // Use Integer to allow NULL values
    private String description;
    private String linkOrVenue;

    public RecruitmentRoundDTO(){

    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLinkOrVenue() {
        return linkOrVenue;
    }

    public void setLinkOrVenue(String linkOrVenue) {
        this.linkOrVenue = linkOrVenue;
    }

    public RecruitmentRound createRecruitmentRound(){
        RecruitmentRoundId recruitmentRoundId = new RecruitmentRoundId(this.jobId, this.roundNumber);
        RecruitmentRound recruitmentRound = new RecruitmentRound(recruitmentRoundId, this.roundType, this.date, this.time, this.duration, this.description, this.linkOrVenue);
        return recruitmentRound;
    }
}
