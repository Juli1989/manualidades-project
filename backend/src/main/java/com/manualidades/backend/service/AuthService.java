package com.manualidades.backend.service;

import com.manualidades.backend.entity.User;
import com.manualidades.backend.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.manualidades.backend.dto.UserResponse;
import com.manualidades.backend.dto.LoginRequest;
import com.manualidades.backend.dto.LoginResponse;
import com.manualidades.backend.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public UserResponse register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRole("ADMIN");

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

    }

    public LoginResponse login(
            LoginRequest request
    ) {

        User user = userRepository.findByEmail(
                request.getEmail()
        );

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException("Password incorrecta");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return new LoginResponse(token);
    }
}