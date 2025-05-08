package com.rituraj.candidateOnboardingSystem.controller.test;

import com.rituraj.candidateOnboardingSystem.dto.CandidateInsertDTO;
import com.rituraj.candidateOnboardingSystem.dto.CandidateJobDTO;
import com.rituraj.candidateOnboardingSystem.dto.CandidateStatusDTO;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.Status;
import com.rituraj.candidateOnboardingSystem.service.test.CandidateTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test/candidate")
public class CandidateTestController {

    private CandidateTestService candidateTestService;

    public CandidateTestController(CandidateTestService candidateTestService) {
        this.candidateTestService = candidateTestService;
    }

    /*
     * Method Name: getAllCandidates()
     * Usage: Fetches all candidates in the DB
     */
    @GetMapping
    public ResponseEntity<Response<List<Candidate>>> getAllCandidates(){
        return candidateTestService.getAllCandidates();
    }

    /*
     * Method Name: getCandidateById(Long id)
     * Usage: Fetches candidate by id from the DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<Candidate>> getCandidateById(@PathVariable Long id){
        return candidateTestService.getCandidateById(id);
    }

    /*
     * Method Name: getCandidateStatusById(@Valid @RequestBody CandidateStatusDTO candidateStatusDTO)
     * Usage: Fetches candidate's status by id from the DB
     */
    @GetMapping("/status")
    public ResponseEntity<Response<Status>> getCandidateStatusById(@Valid @RequestBody CandidateStatusDTO candidateStatusDTO){
        return candidateTestService.getCandidateStatusById(candidateStatusDTO);
    }

    /*
     * Method Name: addCandidate(CandidateInsertDTO candidateInsertDTO)
     * Usage: Insert new candidate in DB
     */
    @PostMapping
    public ResponseEntity<Response<CandidateInsertDTO>> addCandidate(@Valid @RequestBody CandidateInsertDTO candidateInsertDTO){
        return candidateTestService.addCandidate(candidateInsertDTO);
    }

    /*
     * Method Name: applyCandidateToJob(@Valid @RequestBody CandidateJobDTO candidateJobDTO)
     * Usage: Inserts new status of candidate in DB
     */
    @PostMapping("/job")
    public ResponseEntity<Response<String>> applyCandidateToJob(@Valid @RequestBody CandidateJobDTO candidateJobDTO){
        return candidateTestService.applyCandidateToJob(candidateJobDTO);
    }

//    @PutMapping
//    public ResponseEntity<> updateCandidate(@Valid @RequestBody CandidateUpdateDTO candidateUpdateDTO){
//        return candidateTestService.updateCandidate(candidateUpdateDTO);
//    }

    /*
     * Method Name: deleteCandidateById(Long id)
     * Usage: Removes candidate from the DB
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteCandidateById(@PathVariable Long id){
        return candidateTestService.deleteCandidateById(id);
    }

}
