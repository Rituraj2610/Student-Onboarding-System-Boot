package com.rituraj.candidateOnboardingSystem.repo;
import com.rituraj.candidateOnboardingSystem.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, Long> {
    @Query("SELECT b FROM BankDetails b WHERE b.candidate.id = :id")
    Optional<BankDetails> findByCandidateId(Long id);
}
