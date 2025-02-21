package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling user authentication and registration requests.
 * Provides API endpoints for signing up and logging in users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // Constructor injection for UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for user registration.
     * Validates the input data and attempts to register a new user.
     *
     * @param userDTO The user data transfer object containing username and password.
     * @return ResponseEntity with success or error message.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO); // Registers the user without storing the return value
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    /**
     * Endpoint for user login.
     * Checks credentials and returns success or failure response.
     *
     * @param userDTO The user data transfer object containing username and password.
     * @return ResponseEntity with success message and username if authenticated, otherwise an error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        boolean isAuthenticated = userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());

        if (isAuthenticated) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("username", userDTO.getUsername());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }
    }
}
