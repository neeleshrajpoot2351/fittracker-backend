package com.fitnessTracker.service;

import com.fitnessTracker.dto.LogWorkoutRequest;
import com.fitnessTracker.dto.WorkoutLogResponse;
import com.fitnessTracker.model.Routine;
import com.fitnessTracker.model.User;
import com.fitnessTracker.model.WorkoutLog;
import com.fitnessTracker.repository.RoutineRepository;
import com.fitnessTracker.repository.UserRepository;
import com.fitnessTracker.repository.WorkoutLogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    @Autowired private WorkoutLogRepository workoutLogRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private RoutineRepository routineRepository;

    public WorkoutLogResponse logWorkout(Long routineId, LogWorkoutRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + request.getUsername()));
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found with id: " + routineId));

        WorkoutLog log = new WorkoutLog();
        log.setUser(user);
        log.setRoutine(routine);
        log.setCompletedAt(LocalDateTime.now());
        log.setDurationMinutes(routine.getDurationMinutes());
        log.setNotes(request.getNotes());

        WorkoutLog savedLog = workoutLogRepository.save(log);
        return toResponse(savedLog);
    }

    public List<WorkoutLogResponse> getWorkoutHistory(String username) {
        return workoutLogRepository.findByUserUsernameOrderByCompletedAtDesc(username)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private WorkoutLogResponse toResponse(WorkoutLog log) {
        WorkoutLogResponse res = new WorkoutLogResponse();
        res.setId(log.getId());
        res.setRoutineName(log.getRoutine().getName());
        res.setCompletedAt(log.getCompletedAt());
        res.setDurationMinutes(log.getDurationMinutes());
        res.setNotes(log.getNotes());
        return res;
    }
}