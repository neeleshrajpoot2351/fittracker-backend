//package com.fitnessTracker.dto;
//
//public class SignupRequest {
//    private String username;
//    private String email;
//    private String password;
//
//    // getters and setters
//}


 package com.fitnessTracker.dto;

public class SignupRequest {
    private String username;
    private String name;
    private String email;
    private String password;

    // ===== Getters =====
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // ===== Setters =====
    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
