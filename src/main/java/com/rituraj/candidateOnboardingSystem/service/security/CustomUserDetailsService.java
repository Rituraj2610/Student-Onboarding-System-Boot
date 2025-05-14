package com.rituraj.candidateOnboardingSystem.service.security;

import com.rituraj.candidateOnboardingSystem.model.LoginCandidate;
import com.rituraj.candidateOnboardingSystem.repo.LoginCandidateRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginCandidateRepository loginCandidateRepository;

    public CustomUserDetailsService(LoginCandidateRepository loginCandidateRepository) {
        this.loginCandidateRepository = loginCandidateRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginCandidate loginCandidate = loginCandidateRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(loginCandidate.getUsername())
                .password(loginCandidate.getPassword())
                .roles("USER")
                .build();
    }
}