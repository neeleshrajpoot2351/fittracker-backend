package com.fitnessTracker.dto;

public class RoutineRequest {
    private String name;
    private String description;
    private int durationMinutes;
    private String difficulty;
    private String username;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}