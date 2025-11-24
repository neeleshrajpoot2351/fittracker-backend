package com.fitnessTracker.controller;

import com.fitnessTracker.dto.*;
import com.fitnessTracker.model.User;
import com.fitnessTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.fitnessTracker.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils; // <-- 1. INJECT your JwtUtils bean

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        try {
            // Validate input
            if (signUpRequest.getUsername() == null || signUpRequest.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }
            if (signUpRequest.getName() == null || signUpRequest.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (signUpRequest.getEmail() == null || signUpRequest.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (signUpRequest.getPassword() == null || signUpRequest.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }
            
            // Check if username or email already exists
            if (userService.usernameExists(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body("Username already taken! Please choose a different username.");
            }
            if (userService.emailExists(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body("Email already in use! Please use a different email.");
            }

            // Create and save user
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setName(signUpRequest.getName());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(signUpRequest.getPassword());

            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Check if user is logging in with email or username
            String loginIdentifier = loginRequest.getUsername();
            User user = null;
            
            // Try to find user by email first, if it contains @
            if (loginIdentifier.contains("@")) {
                user = userService.getUserByEmail(loginIdentifier);
                if (user == null) {
                    return ResponseEntity.badRequest().body("User not found with this email");
                }
                loginIdentifier = user.getUsername(); // Use actual username for authentication
            }
            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginIdentifier,
                            loginRequest.getPassword()
                    )
            );

            // Get the username from the authenticated object
            String username = authentication.getName();

            // Generate the JWT token
            String jwtToken = jwtUtils.generateJwtToken(username);

            // Get user details including name (if not already fetched)
            if (user == null) {
                user = userService.getUserByUsername(username);
            }
            
            // Return the token along with user info
            return ResponseEntity.ok(new JwtResponseWithUser(jwtToken, user.getUsername(), user.getName(), user.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed. Please check your credentials.");
        }
    }
}

