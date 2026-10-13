package com.college.eventbooking.controller;

import com.college.eventbooking.entity.User;
import com.college.eventbooking.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    private Map<String, Object> safeUser(User user) {
        return Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
    }

    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestBody User user) {
        return safeUser(service.register(user));
    }

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            throw new IllegalArgumentException(
                    "Email and password are required");
        }

        return safeUser(service.login(email, password));
    }
}