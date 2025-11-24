package com.fitnessTracker.service;

import com.fitnessTracker.dto.FoodLogRequest;
import com.fitnessTracker.dto.FoodLogResponse;
import com.fitnessTracker.model.FoodLog;
import com.fitnessTracker.model.User;
import com.fitnessTracker.repository.FoodLogRepository;
import com.fitnessTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DietService {

    @Autowired
    private FoodLogRepository foodLogRepository;

    @Autowired
    private UserRepository userRepository;

    public FoodLogResponse createFoodLog(FoodLogRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + request.getUsername()));

        FoodLog foodLog = new FoodLog();
        foodLog.setUser(user);
        foodLog.setFoodName(request.getFoodName());
        foodLog.setCalories(request.getCalories());
        foodLog.setProteinGrams(request.getProteinGrams());
        foodLog.setCarbsGrams(request.getCarbsGrams());
        foodLog.setFatGrams(request.getFatGrams());
        foodLog.setMealType(request.getMealType());

        FoodLog savedLog = foodLogRepository.save(foodLog);
        return toResponse(savedLog);
    }

    public List<FoodLogResponse> getFoodLogsForUser(String username, Optional<LocalDate> date) {
        List<FoodLog> logs;
        if (date.isPresent()) {
            // If a date is provided, find logs for that entire day
            logs = foodLogRepository.findByUserUsernameAndLogDateBetween(
                    username,
                    date.get().atStartOfDay(),
                    date.get().atTime(LocalTime.MAX)
            );
        } else {
            // Otherwise, get all logs for the user
            logs = foodLogRepository.findByUserUsername(username);
        }
        return logs.stream().map(this::toResponse).collect(Collectors.toList());
    }

    // You can add update and delete methods here as needed

    private FoodLogResponse toResponse(FoodLog log) {
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