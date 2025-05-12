package com.rituraj.candidateOnboardingSystem.service;

import com.rituraj.candidateOnboardingSystem.dto.JobInsertionDTO;
import com.rituraj.candidateOnboardingSystem.dto.JobStatusUpdateDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.JobMapper;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import com.rituraj.candidateOnboardingSystem.rabbitmq.JobProducer;
import com.rituraj.candidateOnboardingSystem.repo.JobRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    private JobRepo jobRepo;
    private JobMapper jobMapper;
    private TechStackService techStackService;
    private JobProducer jobProducer;

    public JobService(JobRepo jobRepo, JobMapper jobMapper, TechStackService techStackService, JobProducer jobProducer) {
        this.jobRepo = jobRepo;
        this.jobMapper = jobMapper;
        this.techStackService = techStackService;
        this.jobProducer = jobProducer;
    }

    public ResponseEntity<Response<List<Job>>> getAllJobs() {
        List<Job> jobList = jobRepo.findAll();

        if(jobList.isEmpty()){
            throw new EntityNotFoundException("No job record found!");
        }

        Response<List<Job>> response = Response.<List<Job>>builder()
                .status(ApiStatus.FOUND)
                .message("Job Found successfully")
                .data(jobList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<Response<List<Job>>> getJobByActiveStatus(Boolean activeStatus) {
        List<Job> jobList = jobRepo.findByActiveStatus(activeStatus);

        if(jobList.isEmpty()){
            throw new EntityNotFoundException("No job record found!");
        }

        Response<List<Job>> response = Response.<List<Job>>builder()
                .status(ApiStatus.FOUND)
                .message("Jobs found successfully!")
                .data(jobList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<String>> addJob(@Valid JobInsertionDTO jobInsertionDTO) {
        Job job = jobMapper.toEntity(jobInsertionDTO);

        List<TechStack> techStacks = jobInsertionDTO.getTechNames().stream()
                .map(techName -> techStackService.getTechStackByName(techName))
                .collect(Collectors.toList());

        job.setTechStackList(techStacks);

        Job j = jobRepo.save(job);

        jobProducer.sendJob(job);

        Response<String> response = Response.<String>builder()
                .status(ApiStatus.CREATED)
                .message("Job created successfully!")
                .data("Job created with id: " + j.getId())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<Response<String>> updateJobDetails(JobStatusUpdateDTO jobStatusUpdateDTO) {
        Optional<Job> optionalJob = jobRepo.findById(jobStatusUpdateDTO.getId());
        if(optionalJob.isEmpty()){
            throw new EntityNotFoundException("Job not found with provided id!");
        }

        Job job = optionalJob.get();
        job.setActiveStatus(jobStatusUpdateDTO.getActiveStatus());

        jobRepo.save(job);

        Response<String> response = Response.<String>builder()
                .status(ApiStatus.OK)
                .message("Status changed successfully!")
                .data("Job with id: " + jobStatusUpdateDTO.getId() + " changed status to " + jobStatusUpdateDTO.getActiveStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
