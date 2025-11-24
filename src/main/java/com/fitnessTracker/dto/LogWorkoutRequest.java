package com.fitnessTracker.dto;

public class LogWorkoutRequest {
    private String username;
    private String notes;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}