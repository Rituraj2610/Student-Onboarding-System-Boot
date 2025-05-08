package com.rituraj.candidateOnboardingSystem.controller;

import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate")
public class CandidateStatusController {

    private CandidateService candidateService;

    public CandidateStatusController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /*
     * Method Name: getAllCandidates()
     * Usage: Fetches all candidates from DB
     */
    @GetMapping
    public ResponseEntity<Response<List<Candidate>>> getAllCandidates(){
        return candidateService.getAllCandidates();
    }


//    @GetMapping("/status")
//    public ResponseEntity<Response<Candidate>> getCandidateByStatus(@RequestParam JobStatus jobStatus){
//        return candidateService.getCandidateByStatus(jobStatus);
//    }


    /*
     * Method Name: getCandidateCount()
     * Usage: Fetches count of candidates from DB
     */
    @GetMapping("/count")
    public ResponseEntity<Response<Integer>> getCandidateCount(){
        return candidateService.getCandidateCount();
    }
}
