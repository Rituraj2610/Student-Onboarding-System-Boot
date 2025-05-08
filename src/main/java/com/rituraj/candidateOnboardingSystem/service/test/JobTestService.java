package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.repo.JobRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobTestService {
    private JobRepo jobRepo;

    public JobTestService(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }


    public Job getJobById(Long id) {
        Optional<Job> optionalJob = jobRepo.findById(id);
        if(optionalJob.isEmpty()){
            // THROW EXCEPTION
        }

        return optionalJob.get();
    }
}
