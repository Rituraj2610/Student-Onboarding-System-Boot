package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.CandidateInsertDTO;
import com.rituraj.candidateOnboardingSystem.dto.CandidateJobDTO;
import com.rituraj.candidateOnboardingSystem.dto.CandidateStatusDTO;
import com.rituraj.candidateOnboardingSystem.dto.CandidateUpdateDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.mapper.CandidateMapper;
import com.rituraj.candidateOnboardingSystem.model.*;
import com.rituraj.candidateOnboardingSystem.repo.CandidateRepo;
import com.rituraj.candidateOnboardingSystem.service.TechStackService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
            // THROW EXCEPTION
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
            //THROW EXCEPTION
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
            //THROW EXCEPTION
        }
        return optionalCandidate.get();
    }

    public ResponseEntity<Response<Status>> getCandidateStatusById(CandidateStatusDTO candidateStatusDTO) {
        return statusTestService.getCandidateStatusById(candidateStatusDTO);
    }

    public ResponseEntity<Response<CandidateInsertDTO>> addCandidate(CandidateInsertDTO candidateInsertDTO) {

        Optional<Candidate> optionalCandidate = candidateRepo.findByEmail(candidateInsertDTO.getEmail());
        if(optionalCandidate.isPresent()){
            //THROW EXCEPTION
        }

        Candidate candidate = candidateMapper.toEntity(candidateInsertDTO);

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
            // THROW EXCEPTION
        }
        Candidate candidate = optionalCandidate.get();
        Job job = jobTestService.getJobById(candidateJobDTO.getJobId());

        List<TechStack> candidateTechStackList = candidate.getTechStackList();
        List<TechStack> jobTechStackList = job.getTechStackList();

        if(!canApply(candidateTechStackList, jobTechStackList)){
//            throw new RuntimeException("ff");
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
            // THROW EXC
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
