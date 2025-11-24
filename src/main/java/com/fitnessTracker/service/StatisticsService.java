package com.fitnessTracker.service;

import com.fitnessTracker.dto.*;
import com.fitnessTracker.model.FoodLog;
import com.fitnessTracker.model.Goal;
import com.fitnessTracker.model.Routine;
import com.fitnessTracker.repository.FoodLogRepository;
import com.fitnessTracker.repository.GoalRepository;
import com.fitnessTracker.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired private RoutineRepository routineRepository;
    @Autowired private GoalRepository goalRepository;
    @Autowired private FoodLogRepository foodLogRepository;
    @Autowired private WorkoutService workoutService;

    public DashboardResponse getDashboardStats(String username) {
        DashboardResponse response = new DashboardResponse();
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();

        long routineCount = routineRepository.findByUserUsername(username).size();
        response.setTotalRoutinesCompleted(routineCount);

        List<Goal> goals = goalRepository.findByUserUsername(username);
        long goalsInProgress = goals.stream()
                .filter(goal -> "IN_PROGRESS".equalsIgnoreCase(goal.getStatus()))
                .count();
        response.setTotalGoalsInProgress(goalsInProgress);

        Integer caloriesToday = foodLogRepository.sumCaloriesForUserSince(username, startOfToday);
        response.setCaloriesConsumedToday(caloriesToday != null ? caloriesToday : 0);

        Integer proteinToday = foodLogRepository.sumProteinForUserSince(username, startOfToday);
        response.setProteinConsumedToday(proteinToday != null ? proteinToday : 0);

        return response;
    }

    public UserHistoryResponse getUserHistory(String username) {
        UserHistoryResponse history = new UserHistoryResponse();

        history.setSummary(getDashboardStats(username));
        history.setRoutines(routineRepository.findByUserUsername(username).stream().map(this::toRoutineResponse).collect(Collectors.toList()));
        history.setGoals(goalRepository.findByUserUsername(username).stream().map(this::toGoalResponse).collect(Collectors.toList()));
        history.setDietLogs(foodLogRepository.findByUserUsername(username).stream().map(this::toFoodLogResponse).collect(Collectors.toList()));
        history.setWorkoutHistory(workoutService.getWorkoutHistory(username));

        return history;
    }

    // Helper methods to convert entities to DTOs
    private RoutineResponse toRoutineResponse(Routine routine) {
        RoutineResponse response = new RoutineResponse();
        response.setId(routine.getId());
        response.setName(routine.getName());
        response.setDescription(routine.getDescription());
        response.setDurationMinutes(routine.getDurationMinutes());
        response.setDifficulty(routine.getDifficulty());
        response.setCreatedAt(routine.getCreatedAt());
        if (routine.getUser() != null) {
            response.setUsername(routine.getUser().getUsername());
        }
        return response;
    }

    private GoalResponse toGoalResponse(Goal goal) {
        GoalResponse response = new GoalResponse();
        response.setId(goal.getId());
        response.setDescription(goal.getDescription());
        response.setTargetDate(goal.getTargetDate());
        response.setStatus(goal.getStatus());
        response.setUsername(goal.getUser().getUsername());
        return response;
    }

    private FoodLogResponse toFoodLogResponse(FoodLog log) {
        FoodLogResponse response = new FoodLogResponse();
        response.setId(log.getId());
        response.setFoodName(log.getFoodName());
        response.setCalories(log.getCalories());
        response.setProteinGrams(log.getProteinGrams());
        response.setCarbsGrams(log.getCarbsGrams());
        response.setFatGrams(log.getFatGrams());
        response.setMealType(log.getMealType());
        response.setLogDate(log.getLogDate());
        response.setUsername(log.getUser().getUsername());
        return response;
    }
}