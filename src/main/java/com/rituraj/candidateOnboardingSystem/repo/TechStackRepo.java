package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.enums.TechName;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechStackRepo extends JpaRepository<TechStack, Long> {

    @Query("SELECT t FROM TechStack t WHERE t.name = :name")
    Optional<TechStack> findByName(TechName name);
}
