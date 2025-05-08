package com.rituraj.candidateOnboardingSystem.service;

import com.rituraj.candidateOnboardingSystem.dto.CandidateStatusUpdateDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.Status;
import com.rituraj.candidateOnboardingSystem.repo.StatusRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private StatusRepo statusRepo;
    private CandidateService candidateService;

    public StatusService(StatusRepo statusRepo, CandidateService candidateService) {
        this.statusRepo = statusRepo;
        this.candidateService = candidateService;
    }

    public ResponseEntity<Response<List<Status>>> getStatusByCandidateId(Long id) {
        List<Status> statusList = statusRepo.findByCandidateId(id);
        if(statusList.isEmpty()){
            throw new EntityNotFoundException("Status not found with provided id!");
        }
        Response<List<Status>> response = Response.<List<Status>>builder()
                .status(ApiStatus.FOUND)
                .message("Status of candidate found successfully!")
                .data(statusList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<String>> updateStatusByCandidateId(CandidateStatusUpdateDTO candidateStatusUpdateDTO) {
        Candidate candidate = candidateService.getCandidateById(candidateStatusUpdateDTO.getCandidateId());
        Optional<Status> optionalStatus = statusRepo.findById(candidateStatusUpdateDTO.getStatusId());
        if(optionalStatus.isEmpty()){
            throw new EntityNotFoundException("Status not found with provided id!");
        }

        Status status = optionalStatus.get();
        if(status.getCandidate().getId() != candidate.getId()){
            throw new EntityNotFoundException("Status not found with provided id!");
        }
        status.setJobStatus(candidateStatusUpdateDTO.getJobStatus());

        statusRepo.save(status);

        Response<String> response = Response.<String>builder()
                .status(ApiStatus.OK)
                .message("Job Status updated successfully!")
                .data("Status of job changed to " + candidateStatusUpdateDTO.getJobStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
