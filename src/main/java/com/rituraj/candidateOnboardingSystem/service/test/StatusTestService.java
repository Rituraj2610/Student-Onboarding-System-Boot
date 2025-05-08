package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.CandidateStatusDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.enums.JobStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.Status;
import com.rituraj.candidateOnboardingSystem.repo.StatusRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusTestService {
    private StatusRepo statusRepo;

    public StatusTestService(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }


    public ResponseEntity<Response<Status>> getCandidateStatusById(CandidateStatusDTO candidateStatusDTO) {
        Optional<Status> optionalStatus = statusRepo.findByIdAndCandidateId(candidateStatusDTO.getStatusId(), candidateStatusDTO.getCandidateId());
        if(optionalStatus.isEmpty()){
            throw new EntityNotFoundException("Status not found with provided id!");
        }
        Response<Status> response = Response.<Status>builder()
                .status(ApiStatus.FOUND)
                .message("Candidate's status found successfully!")
                .data(optionalStatus.get())
                .build();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    public ResponseEntity<Response<String>> applyCandidateToJob(Candidate candidate, Job job) {
        Status status = Status.builder()
                .candidate(candidate)
                .job(job)
                .jobStatus(JobStatus.APPLIED)
                .build();

        Status s = statusRepo.save(status);
        Optional<Status> optionalStatus = statusRepo.findById(s.getId());
        if(optionalStatus.isEmpty()){
            throw new EntityNotFoundException("Status not found with provided id!");
        }

        Response<String> response = Response.<String>builder()
                .status(ApiStatus.CREATED)
                .message("Applied to the job successfully!")
                .data("Application status id: " + s.getId())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
