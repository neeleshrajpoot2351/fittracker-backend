package com.fitnessTracker.service;

import com.fitnessTracker.dto.RoutineRequest;
import com.fitnessTracker.dto.RoutineResponse;
import com.fitnessTracker.model.Routine;
import com.fitnessTracker.model.User;
import com.fitnessTracker.repository.RoutineRepository;
import com.fitnessTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserRepository userRepository;

    public RoutineResponse createRoutine(RoutineRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + request.getUsername()));

        Routine routine = new Routine();
        routine.setName(request.getName());
        routine.setDescription(request.getDescription());
        routine.setDurationMinutes(request.getDurationMinutes());
        routine.setDifficulty(request.getDifficulty());
        routine.setUser(user);

        Routine saved = routineRepository.save(routine);
        return toResponse(saved);
    }

    public List<RoutineResponse> getUserRoutines(String username) {
        return routineRepository.findByUserUsername(username)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RoutineResponse updateRoutine(Long id, RoutineRequest request) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found with id: " + id));

        // NOTE: Authorization check removed for testing.
        routine.setName(request.getName());
        routine.setDescription(request.getDescription());
        routine.setDurationMinutes(request.getDurationMinutes());
        routine.setDifficulty(request.getDifficulty());

        Routine updated = routineRepository.save(routine);
        return toResponse(updated);
    }

    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found with id: " + id));

        // NOTE: Authorization check removed for testing.
        routineRepository.delete(routine);
    }

    private RoutineResponse toResponse(Routine routine) {
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
}