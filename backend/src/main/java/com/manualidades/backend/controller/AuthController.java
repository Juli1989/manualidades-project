package com.manualidades.backend.controller;

import com.manualidades.backend.dto.LoginRequest;
import com.manualidades.backend.dto.LoginResponse;
import com.manualidades.backend.dto.UserResponse;
import com.manualidades.backend.entity.User;
import com.manualidades.backend.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(
            @RequestBody User user
    ) {

        return authService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }
}