package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.model.LoginCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginCandidateRepository extends JpaRepository<LoginCandidate, Long> {
    Optional<LoginCandidate> findByUsername(String username);
    Optional<LoginCandidate> findByEmail(String email);
}
