package com.tpcportal.backend.model;

import java.time.LocalDateTime;

public class VerificationCode {
    private String code;
    private LocalDateTime generatedTime;
    private int expiryMinutes;

    public VerificationCode(String code, int expiryMinutes) {
        this.code = code;
        this.generatedTime = LocalDateTime.now();
        this.expiryMinutes = expiryMinutes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public int getExpiryMinutes() {
        return expiryMinutes;
    }

    public void setExpiryMinutes(int expiryMinutes) {
        this.expiryMinutes = expiryMinutes;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(generatedTime.plusMinutes(expiryMinutes));
    }
}
