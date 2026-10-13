package com.college.eventbooking.controller;

import com.college.eventbooking.entity.User;
import com.college.eventbooking.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final UserService service;

    public StudentController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> getStudents() {
        return service.getAllStudents().stream()
                .map(user -> Map.<String, Object>of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "role", user.getRole()
                ))
                .toList();
    }
}