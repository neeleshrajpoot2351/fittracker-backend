package com.fitnessTracker.dto;

import java.time.LocalDate;

public class GoalRequest {
    private String description;
    private LocalDate targetDate;
    private String status;
    private String username; // To identify the user during creation

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}