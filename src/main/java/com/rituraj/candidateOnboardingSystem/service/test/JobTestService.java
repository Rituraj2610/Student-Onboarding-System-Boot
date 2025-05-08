package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.repo.JobRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JobTestService {
    private JobRepo jobRepo;

    public JobTestService(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    public Job getJobById(Long id) {
        Optional<Job> optionalJob = jobRepo.findById(id);
        if(optionalJob.isEmpty()){
            throw new EntityNotFoundException("Job not found with provided id!");
        }

        return optionalJob.get();
    }
}
