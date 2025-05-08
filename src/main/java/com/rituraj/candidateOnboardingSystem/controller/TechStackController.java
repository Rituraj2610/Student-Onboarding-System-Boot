package com.rituraj.candidateOnboardingSystem.controller;

import com.rituraj.candidateOnboardingSystem.dto.TechStackInsertionDTO;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import com.rituraj.candidateOnboardingSystem.service.TechStackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/techstack")
public class TechStackController {

    private TechStackService techStackService;

    public TechStackController(TechStackService techStackService) {
        this.techStackService = techStackService;
    }

    /*
     * Method Name: getAllTechStack()
     * Usage: Fetches all tech stack from DB
     */
    @GetMapping
    public ResponseEntity<Response<List<TechStack>>> getAllTechStack(){
        return techStackService.getAllTechStack();
    }

    /*
     * Method Name: getTechStackById(@PathVariable Long id)
     * Usage: Fetches tech stack by id from DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<TechStack>> getTechStackById(@PathVariable Long id){
        return techStackService.getTechStackById(id);
    }

    /*
     * Method Name: addTechStack(@Valid @RequestBody TechStackInsertionDTO techStackInsertionDTO)
     * Usage: Insert new Techstack in DB
     */
    @PostMapping
    public ResponseEntity<Response<TechStackInsertionDTO>> addTechStack(@Valid @RequestBody TechStackInsertionDTO techStackInsertionDTO){
        return techStackService.addTechStack(techStackInsertionDTO);
    }
}
