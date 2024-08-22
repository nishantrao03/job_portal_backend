package com.tpcportal.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tpcportal.backend.embeddedids.EligibilityCriteriaId;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "eligibility_criteria")
public class EligibilityCriteria {

    @EmbeddedId
    private EligibilityCriteriaId id;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "cpi_cutoff", precision = 3, scale = 2)
    private BigDecimal cpiCutoff;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;

    public EligibilityCriteria() {

    }

    // Getters and Setters

    public EligibilityCriteriaId getId() {
        return id;
    }

    public EligibilityCriteria(EligibilityCriteriaId id, Integer gender, BigDecimal cpiCutoff) {
        this.id = id;
        this.gender = gender;
        this.cpiCutoff = cpiCutoff;
    }

    public void setId(EligibilityCriteriaId id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public BigDecimal getCpiCutoff() {
        return cpiCutoff;
    }

    public void setCpiCutoff(BigDecimal cpiCutoff) {
        this.cpiCutoff = cpiCutoff;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
