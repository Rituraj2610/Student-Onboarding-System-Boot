package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.model.EducationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationDetailsRepo extends JpaRepository<EducationDetails, Long> {

    @Query("SELECT a FROM EducationDetails a WHERE a.candidate.id = :id")
    Optional<EducationDetails> findByCandidateId(Long id);
}
