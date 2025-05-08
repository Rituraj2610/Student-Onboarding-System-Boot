package com.rituraj.candidateOnboardingSystem.controller;

import com.rituraj.candidateOnboardingSystem.dto.JobInsertionDTO;
import com.rituraj.candidateOnboardingSystem.dto.JobStatusUpdateDTO;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /*
     * Method Name: getAllJobs(
     * Usage: Fetch all jobs
     */
    @GetMapping
    public ResponseEntity<Response<List<Job>>> getAllJobs(){
        return jobService.getAllJobs();
    }

    /*
     * Method Name: getJobByActiveStatus(@RequestParam Boolean activeStatus)
     * Usage: Fetch jobs by status
     */
    @GetMapping("/status")
    public ResponseEntity<Response<List<Job>>> getJobByActiveStatus(@RequestParam Boolean activeStatus){
        return jobService.getJobByActiveStatus(activeStatus);
    }

    /*
     * Method Name: addJob(@Valid @RequestBody JobInsertionDTO jobInsertionDTO)
     * Usage: Insert new job in DB
     */
    @PostMapping
    public ResponseEntity<Response<String>> addJob(@Valid @RequestBody JobInsertionDTO jobInsertionDTO){
        return jobService.addJob(jobInsertionDTO);
    }

//    @PutMapping
//    public ResponseEntity<> updateJobDetails(@RequestBody JobDetailsUpdationDTO jobDetailsUpdationDTO){
//        return jobService.updateJobDetails(jobDetailsUpdationDTO);
//    }

    /*
     * Method Name: updateJobStatus(@RequestBody JobStatusUpdateDTO jobStatusUpdateDTO)
     * Usage: Update the status of job
     */
    @PutMapping("/status")
    public ResponseEntity<Response<String>> updateJobStatus(@RequestBody JobStatusUpdateDTO jobStatusUpdateDTO){
        return jobService.updateJobDetails(jobStatusUpdateDTO);
    }
}
