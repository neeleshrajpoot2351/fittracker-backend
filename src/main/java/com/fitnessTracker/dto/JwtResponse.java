package com.fitnessTracker.dto;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // THIS IS THE MISSING METHOD THAT FIXES THE ERROR
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


//public class JwtResponse {
//    private String token;
////    private String type = "Bearer";
//    private Long id;
//    private String username;
//    private String email;
//    // roles could be added here too
//
//    public JwtResponse(String accessToken, Long id, String username, String email) {
//        this.token = accessToken;
//        this.id = id;
//        this.username = username;
//        this.email = email;
//    }
//
//    // Add Getters and Setters for the new fields...
//}
