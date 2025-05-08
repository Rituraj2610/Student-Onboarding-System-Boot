package com.rituraj.candidateOnboardingSystem.controller.test;

import com.rituraj.candidateOnboardingSystem.dto.EducationDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.EducationDetails;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.service.test.EducationDetailsTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/educationdetails")
public class EducationDetailsTestController {
    private EducationDetailsTestService educationDetailsTestService;

    public EducationDetailsTestController(EducationDetailsTestService educationDetailsTestService) {
        this.educationDetailsTestService = educationDetailsTestService;
    }

    /*
     * Method Name: getEducationDetailsByCandidateId(@PathVariable Long id)
     * Usage: Fetch education details by candidate id from DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<EducationDetails>> getEducationDetailsByCandidateId(@PathVariable Long id){
        return educationDetailsTestService.getEducationDetailsByCandidateId(id);
    }

    /*
     * Method Name: addEducationDetailsByCandidateId(@Valid @RequestBody EducationDetailsInsertDTO educationDetailsInsertDTO)
     * Usage: Inserts education detail by caniddate id in the DB
     */
    @PostMapping
    public ResponseEntity<Response<EducationDetailsInsertDTO>> addEducationDetailsByCandidateId(@Valid @RequestBody EducationDetailsInsertDTO educationDetailsInsertDTO){
        return educationDetailsTestService.addEducationDetailsByCandidateId(educationDetailsInsertDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<> updateEducationDetailsByCandidateId(@PathVariable Long id){
//        return educationDetailsTestService.updateEducationDetailsByCandidateId(id);
//    }
}
