package com.fitnessTracker.controller;

import com.fitnessTracker.dto.UpdateProfileRequest;
import com.fitnessTracker.model.User;
import com.fitnessTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(new UserProfileResponse(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDateOfBirth(),
                user.getHeight(),
                user.getWeight(),
                user.getTargetWeight(),
                user.getFitnessLevel()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String username, @RequestBody UpdateProfileRequest request) {
        try {
            User user = userService.getUserByUsername(username);
            
            if (request.getName() != null && !request.getName().trim().isEmpty()) {
                user.setName(request.getName());
            }
            
            if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
                // Check if email is already taken by another user
                if (!user.getEmail().equals(request.getEmail()) && userService.emailExists(request.getEmail())) {
                    return ResponseEntity.badRequest().body("Email already in use");
                }
                user.setEmail(request.getEmail());
            }
            
            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }

            // Update additional fields
            if (request.getPhone() != null) {
                user.setPhone(request.getPhone());
            }

            if (request.getDateOfBirth() != null) {
                user.setDateOfBirth(request.getDateOfBirth());
            }

            if (request.getHeight() != null) {
                user.setHeight(request.getHeight());
            }

            if (request.getWeight() != null) {
                user.setWeight(request.getWeight());
            }

            if (request.getTargetWeight() != null) {
                user.setTargetWeight(request.getTargetWeight());
            }

            if (request.getFitnessLevel() != null) {
                user.setFitnessLevel(request.getFitnessLevel());
            }
            
            userService.updateUser(user);
            return ResponseEntity.ok(new UserProfileResponse(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDateOfBirth(),
                user.getHeight(),
                user.getWeight(),
                user.getTargetWeight(),
                user.getFitnessLevel()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Profile update failed: " + e.getMessage());
        }
    }

    // Inner class for user profile response
    public static class UserProfileResponse {
        private String username;
        private String name;
        private String email;
        private String phone;
        private String dateOfBirth;
        private Double height;
        private Double weight;
        private Double targetWeight;
        private String fitnessLevel;

        public UserProfileResponse(String username, String name, String email, String phone, String dateOfBirth, 
                                 Double height, Double weight, Double targetWeight, String fitnessLevel) {
            this.username = username;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.dateOfBirth = dateOfBirth;
            this.height = height;
            this.weight = weight;
            this.targetWeight = targetWeight;
            this.fitnessLevel = fitnessLevel;
        }

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public Double getTargetWeight() {
            return targetWeight;
        }

        public void setTargetWeight(Double targetWeight) {
            this.targetWeight = targetWeight;
        }

        public String getFitnessLevel() {
            return fitnessLevel;
        }

        public void setFitnessLevel(String fitnessLevel) {
            this.fitnessLevel = fitnessLevel;
        }
    }
}

