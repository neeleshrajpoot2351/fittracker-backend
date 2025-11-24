package com.fitnessTracker.controller;

import com.fitnessTracker.dto.FoodLogRequest;
import com.fitnessTracker.dto.FoodLogResponse;
import com.fitnessTracker.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/log")
    public ResponseEntity<FoodLogResponse> createFoodLog(@RequestBody FoodLogRequest request) {
        return ResponseEntity.ok(dietService.createFoodLog(request));
    }

    @GetMapping("/log/user/{username}")
    public ResponseEntity<List<FoodLogResponse>> getFoodLogs(
            @PathVariable String username,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        return ResponseEntity.ok(dietService.getFoodLogsForUser(username, date));
    }
}