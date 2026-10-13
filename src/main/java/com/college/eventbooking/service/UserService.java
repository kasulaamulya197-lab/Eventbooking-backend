package com.college.eventbooking.service;

import com.college.eventbooking.entity.User;
import com.college.eventbooking.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllStudents() {
        return repository.findAll();
    }

    public User register(User user) {
        if (user.getName() == null ||
                user.getName().isBlank() ||
                user.getEmail() == null ||
                user.getEmail().isBlank() ||
                user.getPassword() == null ||
                user.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "Name, email and password are required");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        user.setRole("STUDENT");

        // Demonstration only: replace with BCrypt password hashing
        // before using this application with real users.
        return repository.save(user);
    }

    public User login(String email, String password) {
        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        // Demonstration only: passwords are currently plain text.
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(
                    "Invalid email or password");
        }

        return user;
    }
}