package com.fitnessTracker.controller;

import com.fitnessTracker.dto.RoutineRequest;
import com.fitnessTracker.dto.RoutineResponse;
import com.fitnessTracker.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(@RequestBody RoutineRequest request) {
        return ResponseEntity.ok(routineService.createRoutine(request));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<RoutineResponse>> getUserRoutines(@PathVariable String username) {
        return ResponseEntity.ok(routineService.getUserRoutines(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponse> updateRoutine(@PathVariable Long id,
                                                         @RequestBody RoutineRequest request) {
        return ResponseEntity.ok(routineService.updateRoutine(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.ok("Routine deleted successfully!");
    }
}