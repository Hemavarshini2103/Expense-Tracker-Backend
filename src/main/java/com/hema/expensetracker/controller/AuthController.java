package com.hema.expensetracker.controller;

import com.hema.expensetracker.dto.LoginRequest;
import com.hema.expensetracker.dto.LoginResponse;
import com.hema.expensetracker.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import com.hema.expensetracker.dto.RegisterRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    // Constructor Injection (Recommended in Spring Boot 3)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("Login successful");

        return response;
    }
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword());

        return "Registration Successful";
    }
}