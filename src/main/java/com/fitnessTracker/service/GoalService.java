package com.fitnessTracker.service;

import com.fitnessTracker.dto.GoalRequest;
import com.fitnessTracker.dto.GoalResponse;
import com.fitnessTracker.model.Goal;
import com.fitnessTracker.model.User;
import com.fitnessTracker.repository.GoalRepository;
import com.fitnessTracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    public GoalResponse createGoal(GoalRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + request.getUsername()));

        Goal goal = new Goal();
        goal.setDescription(request.getDescription());
        goal.setTargetDate(request.getTargetDate());
        goal.setStatus(request.getStatus());
        goal.setUser(user);

        Goal savedGoal = goalRepository.save(goal);
        return toResponse(savedGoal);
    }

    public List<GoalResponse> getGoalsByUser(String username) {
        return goalRepository.findByUserUsername(username)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public GoalResponse updateGoal(Long id, GoalRequest request) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));

        goal.setDescription(request.getDescription());
        goal.setTargetDate(request.getTargetDate());
        goal.setStatus(request.getStatus());

        Goal updatedGoal = goalRepository.save(goal);
        return toResponse(updatedGoal);
    }

    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new EntityNotFoundException("Goal not found with id: " + id);
        }
        goalRepository.deleteById(id);
    }

    private GoalResponse toResponse(Goal goal) {
        GoalResponse response = new GoalResponse();
        response.setId(goal.getId());
        response.setDescription(goal.getDescription());
        response.setTargetDate(goal.getTargetDate());
        response.setStatus(goal.getStatus());
        response.setUsername(goal.getUser().getUsername());
        return response;
    }
}