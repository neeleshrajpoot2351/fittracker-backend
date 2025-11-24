package com.fitnessTracker.repository;

import com.fitnessTracker.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    /**
     * Finds all routines associated with a specific user by their username.
     * Spring Data JPA creates the query automatically by parsing the method name.
     */
    List<Routine> findByUserUsername(String username);
}