package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.EducationDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.EducationDetailsMapper;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.EducationDetails;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.repo.EducationDetailsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EducationDetailsTestService {
    private EducationDetailsRepo educationDetailsRepo;
    private CandidateTestService candidateTestService;
    private EducationDetailsMapper educationDetailsMapper;

    public EducationDetailsTestService(EducationDetailsRepo educationDetailsRepo, CandidateTestService candidateTestService, EducationDetailsMapper educationDetailsMapper) {
        this.educationDetailsRepo = educationDetailsRepo;
        this.candidateTestService = candidateTestService;
        this.educationDetailsMapper = educationDetailsMapper;
    }

    public ResponseEntity<Response<EducationDetails>> getEducationDetailsByCandidateId(Long id) {
        Optional<EducationDetails> optionalEducationDetails = educationDetailsRepo.findByCandidateId(id);
        if(optionalEducationDetails.isEmpty()){
            throw new EntityNotFoundException("Education Details not found with provided id!");
        }

        Response<EducationDetails> response = Response.<EducationDetails>builder()
                .status(ApiStatus.FOUND)
                .message("Details found successfully!")
                .data(optionalEducationDetails.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<EducationDetailsInsertDTO>> addEducationDetailsByCandidateId(EducationDetailsInsertDTO educationDetailsInsertDTO) {
        Candidate candidate = candidateTestService.getCandidateById2(educationDetailsInsertDTO.getCandidateId());

        EducationDetails educationDetails = educationDetailsMapper.toEntity(educationDetailsInsertDTO);
        educationDetails.setCandidate(candidate);

        candidate.getEducationDetailsList().add(educationDetails);
        // COULD ADD EXCEPTION IF ADDRESS MATCHES
//        educationDetailsRepo.save(educationDetails);

        if(!candidateTestService.addCandidate2(candidate)){
            // THR EXC
        }
        Response<EducationDetailsInsertDTO> response = Response.<EducationDetailsInsertDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Details created successfully")
                .data(educationDetailsInsertDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
