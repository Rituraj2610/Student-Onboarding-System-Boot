package com.rituraj.candidateOnboardingSystem.controller;

import com.rituraj.candidateOnboardingSystem.dto.ForgotPasswordRequest;
import com.rituraj.candidateOnboardingSystem.dto.LoginRequest;
import com.rituraj.candidateOnboardingSystem.dto.ResetPasswordRequest;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.LoginCandidate;
import com.rituraj.candidateOnboardingSystem.service.security.AuthService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginCandidate candidate) {
        return ResponseEntity.ok(authService.register(candidate));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        return ResponseEntity.ok(authService.forgotPassword(request.getEmail()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(authService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword()));
    }
}




