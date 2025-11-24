package com.fitnessTracker.controller;

import com.fitnessTracker.dto.LogWorkoutRequest;
import com.fitnessTracker.dto.WorkoutLogResponse;
import com.fitnessTracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/log/{routineId}")
    public ResponseEntity<WorkoutLogResponse> logWorkout(@PathVariable Long routineId, @RequestBody LogWorkoutRequest request) {
        return ResponseEntity.ok(workoutService.logWorkout(routineId, request));
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<WorkoutLogResponse>> getWorkoutHistory(@PathVariable String username) {
        return ResponseEntity.ok(workoutService.getWorkoutHistory(username));
    }
}