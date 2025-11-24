package com.fitnessTracker.dto;

public class DashboardResponse {

    private long totalRoutinesCompleted;
    private long totalGoalsInProgress;
    private Integer caloriesConsumedToday;
    private Integer proteinConsumedToday;

    // Getters and Setters
    public long getTotalRoutinesCompleted() { return totalRoutinesCompleted; }
    public void setTotalRoutinesCompleted(long totalRoutinesCompleted) { this.totalRoutinesCompleted = totalRoutinesCompleted; }

    public long getTotalGoalsInProgress() { return totalGoalsInProgress; }
    public void setTotalGoalsInProgress(long totalGoalsInProgress) { this.totalGoalsInProgress = totalGoalsInProgress; }

    public Integer getCaloriesConsumedToday() { return caloriesConsumedToday; }
    public void setCaloriesConsumedToday(Integer caloriesConsumedToday) { this.caloriesConsumedToday = caloriesConsumedToday; }

    public Integer getProteinConsumedToday() { return proteinConsumedToday; }
    public void setProteinConsumedToday(Integer proteinConsumedToday) { this.proteinConsumedToday = proteinConsumedToday; }
}