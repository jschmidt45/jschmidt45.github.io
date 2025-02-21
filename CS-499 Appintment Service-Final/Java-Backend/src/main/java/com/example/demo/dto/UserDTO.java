package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for user registration and authentication.
 * Validates the username and password fields before processing.
 */
public class UserDTO {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,15}$", 
             message = "Username must be 4-15 characters long and contain only letters, numbers, or underscores.")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
