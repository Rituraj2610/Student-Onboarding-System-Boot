package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.activeStatus = :activeStatus")
    List<Job> findByActiveStatus(Boolean activeStatus);
}
