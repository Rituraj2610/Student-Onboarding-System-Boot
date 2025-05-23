package com.rituraj.candidateOnboardingSystem.service;

import com.rituraj.candidateOnboardingSystem.dto.TechStackInsertionDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.enums.TechName;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.TechStackMapper;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import com.rituraj.candidateOnboardingSystem.repo.TechStackRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechStackService {
    private TechStackRepo techStackRepo;
    private TechStackMapper techStackMapper;

    public TechStackService(TechStackRepo techStackRepo, TechStackMapper techStackMapper) {
        this.techStackRepo = techStackRepo;
        this.techStackMapper = techStackMapper;
    }

    public ResponseEntity<Response<List<TechStack>>> getAllTechStack() {
        List<TechStack> techStackList = techStackRepo.findAll();
        if(techStackList.isEmpty()){
            throw new EntityNotFoundException("No TechStack records found!");
        }

        Response<List<TechStack>> listResponse = Response.<List<TechStack>>builder()
                .status(ApiStatus.FOUND)
                .message("Tech Stack found successfully!")
                .data(techStackList)
                .build();
        return new ResponseEntity<>(listResponse, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<TechStack>> getTechStackById(Long id) {
        Optional<TechStack> optionalTechStack = techStackRepo.findById(id);
        if(optionalTechStack.isEmpty()){
            throw new EntityNotFoundException("Tech Stack not found with provided id!");
        }
        Response<TechStack> response = Response.<TechStack>builder()
                .status(ApiStatus.FOUND)
                .message("Tech Stack found successfully!")
                .data(optionalTechStack.get())
                .build();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<TechStackInsertionDTO>> addTechStack(TechStackInsertionDTO techStackInsertionDTO) {
        Optional<TechStack> optionalTechStack = techStackRepo.findByName(techStackInsertionDTO.getName());
        if(optionalTechStack.isPresent()){
            throw new EntityNotFoundException("Tech Stack not found with provided id!");
        }
        TechStack techStack = techStackMapper.toEntity(techStackInsertionDTO);
        techStackRepo.save(techStack);
        Response<TechStackInsertionDTO> response = Response.<TechStackInsertionDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Tech Stack added successfully!")
                .data(techStackInsertionDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public TechStack getTechStackByName(TechName techName){
        Optional<TechStack> optionalTechStack = techStackRepo.findByName(techName);
        if(optionalTechStack.isEmpty()){
            throw new EntityNotFoundException("Tech Stack not found with provided id!");
        }
        return optionalTechStack.get();
    }
}
