package com.fitnessTracker.repository;

import com.fitnessTracker.model.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {
    // Finds all logs for a user, ordered by most recent first
    List<WorkoutLog> findByUserUsernameOrderByCompletedAtDesc(String username);
}