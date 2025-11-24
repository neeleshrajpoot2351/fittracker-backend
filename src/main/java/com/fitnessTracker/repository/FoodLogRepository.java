//package com.fitnessTracker.repository;
//
//import com.fitnessTracker.model.FoodLog;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {
//    // Finds all logs for a specific user
//    List<FoodLog> findByUserUsername(String username);
//
//    // Finds all logs for a user within a specific date range (e.g., for one day)
//    List<FoodLog> findByUserUsernameAndLogDateBetween(String username, LocalDateTime start, LocalDateTime end);
//}
//



package com.fitnessTracker.repository;

import com.fitnessTracker.model.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {

    // Finds all logs for a specific user
    List<FoodLog> findByUserUsername(String username);

    // Finds all logs for a user within a specific date range (e.g., for one day)
    List<FoodLog> findByUserUsernameAndLogDateBetween(String username, LocalDateTime start, LocalDateTime end);

    // Custom query to sum calories for the statistics feature
    @Query("SELECT SUM(f.calories) FROM FoodLog f WHERE f.user.username = :username AND f.logDate >= :startDate")
    Integer sumCaloriesForUserSince(@Param("username") String username, @Param("startDate") LocalDateTime startDate);

    // Custom query to sum protein for the statistics feature
    @Query("SELECT SUM(f.proteinGrams) FROM FoodLog f WHERE f.user.username = :username AND f.logDate >= :startDate")
    Integer sumProteinForUserSince(@Param("username") String username, @Param("startDate") LocalDateTime startDate);
}