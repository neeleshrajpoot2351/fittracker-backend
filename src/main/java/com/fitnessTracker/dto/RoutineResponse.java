package com.fitnessTracker.dto;

import java.time.LocalDateTime;

public class RoutineResponse {
    private Long id;
    private String name;
    private String description;
    private int durationMinutes;
    private String difficulty;
    private LocalDateTime createdAt;
    private String username; // Field added to include the user's username

    // A no-argument constructor is needed for the service logic to work
    public RoutineResponse() {
    }

    // The all-arguments constructor is updated
    public RoutineResponse(Long id, String name, String description, int durationMinutes, String difficulty, LocalDateTime createdAt, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
        this.username = username;
    }

    // --- Getters ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getDifficulty() { return difficulty; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getUsername() { return username; }

    // --- Setters (Required by RoutineService) ---
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUsername(String username) { this.username = username; }
}