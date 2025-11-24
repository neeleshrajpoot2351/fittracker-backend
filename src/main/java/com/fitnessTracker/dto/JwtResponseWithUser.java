package com.fitnessTracker.dto;

public class JwtResponseWithUser {
    private String token;
    private String username;
    private String name;
    private String email;

    public JwtResponseWithUser(String token, String username, String name, String email) {
        this.token = token;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
}

