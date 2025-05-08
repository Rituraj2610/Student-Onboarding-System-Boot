package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.*;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.CandidateApplicationRejectedException;
import com.rituraj.candidateOnboardingSystem.exception.DuplicateEntityException;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.CandidateMapper;
import com.rituraj.candidateOnboardingSystem.model.*;
import com.rituraj.candidateOnboardingSystem.repo.CandidateRepo;
import com.rituraj.candidateOnboardingSystem.service.TechStackService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class CandidateTestService {

    private CandidateRepo candidateRepo;
    private StatusTestService statusTestService;
    private CandidateMapper candidateMapper;
    private JobTestService jobTestService;
    private TechStackService techStackService;

    public CandidateTestService(CandidateRepo candidateRepo, StatusTestService statusTestService, CandidateMapper candidateMapper, JobTestService jobTestService, TechStackService techStackService) {
        this.candidateRepo = candidateRepo;
        this.statusTestService = statusTestService;
        this.candidateMapper = candidateMapper;
        this.jobTestService = jobTestService;
        this.techStackService = techStackService;
    }

    public ResponseEntity<Response<List<Candidate>>> getAllCandidates() {
        List<Candidate> candidateList = candidateRepo.findAll();
        if(candidateList.isEmpty()){
            throw new EntityNotFoundException("No candidate found in records!");
        }

        Response<List<Candidate>> response = Response.<List<Candidate>>builder()
                .status(ApiStatus.FOUND)
                .message("All candidates fetched successfully!")
                .data(candidateList)
                .build();

        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    public ResponseEntity<Response<Candidate>> getCandidateById(Long id) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(id);
        if(optionalCandidate.isEmpty()){
            throw new EntityNotFoundException("Candidate not found with provided id!");
        }

        Response<Candidate> response = Response.<Candidate>builder()
                .status(ApiStatus.FOUND)
                .message("Candidate found successfully!")
                .data(optionalCandidate.get())
                .build();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public Candidate getCandidateById2(Long id) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(id);
        if(optionalCandidate.isEmpty()){
            throw new EntityNotFoundException("Candidate not found with provided id!");
        }
        return optionalCandidate.get();
    }

    public ResponseEntity<Response<Status>> getCandidateStatusById(CandidateStatusDTO candidateStatusDTO) {
        return statusTestService.getCandidateStatusById(candidateStatusDTO);
    }

    public ResponseEntity<Response<CandidateInsertDTO>> addCandidate(CandidateInsertDTO candidateInsertDTO) {

        Optional<Candidate> optionalCandidate = candidateRepo.findByEmail(candidateInsertDTO.getEmail());
        if(optionalCandidate.isPresent()){
            throw new DuplicateEntityException("Duplicate candidate not allowed!");
        }

        Candidate candidate = candidateMapper.toEntity(candidateInsertDTO);

        // WE WANTED TO MAP THE TECH NAMES IN DTO WITH TECH STACK OBJECT AND ADD THEM IN THE LIST
        List<TechStack> techStacks = candidateInsertDTO.getTechNames().stream()
                .map(techName -> techStackService.getTechStackByName(techName))
                .collect(Collectors.toList());

        candidate.setTechStackList(techStacks);

        candidateRepo.save(candidate);
        Response<CandidateInsertDTO> response = Response.<CandidateInsertDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Candidate inserted successfully!")
                .data(candidateInsertDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public boolean addCandidate2(Candidate candidate) {
        candidateRepo.save(candidate);
        return true;
    }

    public ResponseEntity<Response<String>> applyCandidateToJob(CandidateJobDTO candidateJobDTO) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(candidateJobDTO.getCandidateId());
        if(optionalCandidate.isEmpty()){
            throw new EntityNotFoundException("Candidate not found with provided id!");
        }
        Candidate candidate = optionalCandidate.get();
        Job job = jobTestService.getJobById(candidateJobDTO.getJobId());

        List<TechStack> candidateTechStackList = candidate.getTechStackList();
        List<TechStack> jobTechStackList = job.getTechStackList();

        if(!canApply(candidateTechStackList, jobTechStackList)){
            throw new CandidateApplicationRejectedException("Candidate doesn't fulfill the Job criteria!");
        }

        return statusTestService.applyCandidateToJob(candidate, job);
    }

    private boolean canApply(List<TechStack> l1, List<TechStack> l2){
        long count = l1.stream()
                        .filter(l2::contains)
                .count();

        if((int) count > (0.75 * l2.size())){
            return true;
        }

        return false;
    }


    public ResponseEntity<Response<String>> deleteCandidateById(Long id) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(id);

        if(optionalCandidate.isEmpty()){
            throw new EntityNotFoundException("Candidate not found with provided id!");
        }

        candidateRepo.deleteById(id);

        Response<String> response = Response.<String>builder()
                .status(ApiStatus.OK)
                .message("Candidate deleted successfully!")
                .data("Candidate deleted with name: '" + optionalCandidate.get().getName() + "' and email: '" + optionalCandidate.get().getEmail() + "'")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
