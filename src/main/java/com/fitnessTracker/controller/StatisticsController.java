package com.fitnessTracker.controller;

import com.fitnessTracker.dto.DashboardResponse;
import com.fitnessTracker.dto.UserHistoryResponse;
import com.fitnessTracker.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/dashboard/{username}")
    public ResponseEntity<DashboardResponse> getDashboardStats(@PathVariable String username) {
        DashboardResponse stats = statisticsService.getDashboardStats(username);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<UserHistoryResponse> getUserHistory(@PathVariable String username) {
        UserHistoryResponse history = statisticsService.getUserHistory(username);
        return ResponseEntity.ok(history);
    }
}