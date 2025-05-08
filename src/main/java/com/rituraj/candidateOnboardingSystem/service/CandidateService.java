package com.rituraj.candidateOnboardingSystem.service;

import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.repo.CandidateRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private CandidateRepo candidateRepo;

    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    public Candidate getCandidateById(Long candidateId) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(candidateId);
        if (optionalCandidate.isEmpty()){
            throw new EntityNotFoundException("Candidate not found with provided id!");
        }

        return optionalCandidate.get();
    }

    public ResponseEntity<Response<List<Candidate>>> getAllCandidates() {
        List<Candidate> candidateList = candidateRepo.findAll();
        if(candidateList.isEmpty()){
            throw new EntityNotFoundException("No candidate record found!");
        }
        Response<List<Candidate>> response = Response.<List<Candidate>>builder()
                .status(ApiStatus.FOUND)
                .message("Candidates found successfully!")
                .data(candidateList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    public ResponseEntity<Response<Integer>> getCandidateCount() {
        List<Candidate> candidateList = candidateRepo.findAll();
        if(candidateList.isEmpty()){
            throw new EntityNotFoundException("No candidate record found!");
        }
        Response<Integer> response = Response.<Integer>builder()
                .status(ApiStatus.OK)
                .message("Candidate's count fetched successfully!")
                .data(candidateList.size())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
