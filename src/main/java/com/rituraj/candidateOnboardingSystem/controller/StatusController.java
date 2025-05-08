package com.rituraj.candidateOnboardingSystem.controller;

import com.rituraj.candidateOnboardingSystem.dto.CandidateStatusUpdateDTO;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.model.Status;
import com.rituraj.candidateOnboardingSystem.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /*
     * Method Name: getStatusByCandidateId(@RequestParam Long id)
     * Usage: Fetches all status of a candidate from DB
     */
    @GetMapping
    public ResponseEntity<Response<List<Status>>> getStatusByCandidateId(@RequestParam Long id){
        return statusService.getStatusByCandidateId(id);
    }

    /*
     * Method Name: updateStatusByCandidateId(@Valid @RequestBody CandidateStatusUpdateDTO candidateStatusUpdateDTO)
     * Usage: Updates status by candidate ID
     */

    @PutMapping
    public ResponseEntity<Response<String>> updateStatusByCandidateId(@Valid @RequestBody CandidateStatusUpdateDTO candidateStatusUpdateDTO){
        return statusService.updateStatusByCandidateId(candidateStatusUpdateDTO);
    }
}
