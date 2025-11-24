package com.fitnessTracker.repository;

import com.fitnessTracker.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    // Finds all goals for a user based on their username
    List<Goal> findByUserUsername(String username);
}