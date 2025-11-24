package com.fitnessTracker.dto;

import java.util.List;

public class UserHistoryResponse {

    private DashboardResponse summary;
    private List<RoutineResponse> routines;
    private List<GoalResponse> goals;
    private List<FoodLogResponse> dietLogs;
    private List<WorkoutLogResponse> workoutHistory;

    // Getters and Setters
    public DashboardResponse getSummary() { return summary; }
    public void setSummary(DashboardResponse summary) { this.summary = summary; }

    public List<RoutineResponse> getRoutines() { return routines; }
    public void setRoutines(List<RoutineResponse> routines) { this.routines = routines; }

    public List<GoalResponse> getGoals() { return goals; }
    public void setGoals(List<GoalResponse> goals) { this.goals = goals; }

    public List<FoodLogResponse> getDietLogs() { return dietLogs; }
    public void setDietLogs(List<FoodLogResponse> dietLogs) { this.dietLogs = dietLogs; }

    public List<WorkoutLogResponse> getWorkoutHistory() { return workoutHistory; }
    public void setWorkoutHistory(List<WorkoutLogResponse> workoutHistory) { this.workoutHistory = workoutHistory; }
}