package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {

    @Query("SELECT s FROM Status s WHERE s.id = :id AND s.candidate.id = :candidateId")
    Optional<Status> findByIdAndCandidateId(@Param("id") Long statusId, @Param("candidateId") Long candidateId);

    @Query("SELECT s FROM Status s WHERE s.candidate.id = :id")
    List<Status> findByCandidateId(@Param("id") Long id);
}
