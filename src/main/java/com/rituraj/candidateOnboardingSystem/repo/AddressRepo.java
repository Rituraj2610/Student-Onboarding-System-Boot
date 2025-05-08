package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.candidate.id = :id")
    Optional<Address> findByCandidateId(Long id);
}
