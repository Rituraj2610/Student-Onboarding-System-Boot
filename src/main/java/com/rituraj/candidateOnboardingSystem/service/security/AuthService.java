package com.rituraj.candidateOnboardingSystem.service.security;

import com.rituraj.candidateOnboardingSystem.model.LoginCandidate;
import com.rituraj.candidateOnboardingSystem.repo.LoginCandidateRepository;
import com.rituraj.candidateOnboardingSystem.util.JwtUtil;
import jakarta.mail.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final LoginCandidateRepository loginCandidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public AuthService(LoginCandidateRepository loginCandidateRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil, EmailService emailService) {
        this.loginCandidateRepository = loginCandidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    public String register(LoginCandidate loginCandidate) {
        if (loginCandidateRepository.findByUsername(loginCandidate.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (loginCandidateRepository.findByEmail(loginCandidate.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        loginCandidate.setPassword(passwordEncoder.encode(loginCandidate.getPassword()));
        loginCandidateRepository.save(loginCandidate);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                username, password, new java.util.ArrayList<>()));
    }

    public String forgotPassword(String email) throws MessagingException {
        LoginCandidate loginCandidate = loginCandidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        String otp = generateOtp();
        loginCandidate.setOtp(otp);
        loginCandidateRepository.save(loginCandidate);
        emailService.sendOtpEmail(email, otp);
        return "OTP sent to email";
    }

    public String resetPassword(String email, String otp, String newPassword) {
        LoginCandidate loginCandidate = loginCandidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        if (!otp.equals(loginCandidate.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }
        loginCandidate.setPassword(passwordEncoder.encode(newPassword));
        loginCandidate.setOtp(null);
        loginCandidateRepository.save(loginCandidate);
        return "Password reset successfully";
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}