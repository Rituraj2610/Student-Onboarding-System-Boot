package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.dto.CandidateRabbitDTO;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {


//    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.educationDetailsList LEFT JOIN FETCH c.documentList LEFT JOIN FETCH c.addressList LEFT JOIN FETCH c.bankDetailsList LEFT JOIN FETCH c.statusList WHERE c.id = :id")
//    Optional<Candidate> findById(Long id);

    @Query("SELECT c FROM Candidate c WHERE c.email = :email")
    Optional<Candidate> findByEmail(String email);

    @Query("SELECT new com.rituraj.candidateOnboardingSystem.dto.CandidateRabbitDTO(" +
            "c.name, c.email, " +
            "(SELECT COALESCE(CAST(GROUP_CONCAT(t.name) AS STRING), '') " +
            "FROM TechStack t WHERE t IN ELEMENTS(c.techStackList))) " +
            "FROM Candidate c")
    List<CandidateRabbitDTO> findAllCandidateDTOs();
}
