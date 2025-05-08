package com.rituraj.candidateOnboardingSystem.repo;
import com.rituraj.candidateOnboardingSystem.model.BankDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, Long> {
    @Query("SELECT b FROM BankDetails b WHERE b.candidate.id = :id")
    Optional<BankDetails> findByCandidateId(Long id);

    @Query("SELECT b FROM BankDetails b WHERE b.accountNumber = :accountNumber")
    Optional<BankDetails> findByAccountNumber(@Param("accountNumber") String accountNumber);
}
