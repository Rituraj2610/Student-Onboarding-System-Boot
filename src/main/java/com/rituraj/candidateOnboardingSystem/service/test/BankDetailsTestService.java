package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.BankDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.DuplicateEntityException;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.BankDetailsMapper;
import com.rituraj.candidateOnboardingSystem.model.Address;
import com.rituraj.candidateOnboardingSystem.model.BankDetails;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.repo.BankDetailsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BankDetailsTestService {

    private BankDetailsRepo bankDetailsRepo;
    private CandidateTestService candidateTestService;
    private BankDetailsMapper bankDetailsMapper;

    public BankDetailsTestService(BankDetailsRepo bankDetailsRepo, CandidateTestService candidateTestService, BankDetailsMapper bankDetailsMapper) {
        this.bankDetailsRepo = bankDetailsRepo;
        this.candidateTestService = candidateTestService;
        this.bankDetailsMapper = bankDetailsMapper;
    }

    public ResponseEntity<Response<BankDetails> > getBankDetailsByCandidateId(Long id) {
        Optional<BankDetails> optionalBankDetails = bankDetailsRepo.findByCandidateId(id);
        if(optionalBankDetails.isEmpty()){
            throw new EntityNotFoundException("Bank Details not found with provided id!");
        }

        Response<BankDetails> response = Response.<BankDetails>builder()
                .status(ApiStatus.FOUND)
                .message("Address found successfully!")
                .data(optionalBankDetails.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<BankDetailsInsertDTO>> addBankDetailsByCandidateId(BankDetailsInsertDTO bankDetailsInsertDTO) {
        Optional<BankDetails> optionalBankDetails = bankDetailsRepo.findByAccountNumber(bankDetailsInsertDTO.getAccountNumber());
        if(optionalBankDetails.isPresent()){
            throw new DuplicateEntityException("Duplicate bank details not allowed!");
        }

        Candidate candidate = candidateTestService.getCandidateById2(bankDetailsInsertDTO.getCandidateId());
        BankDetails bankDetails = bankDetailsMapper.toEntity(bankDetailsInsertDTO);
        bankDetails.setCandidate(candidate);
        bankDetailsRepo.save(bankDetails);

        Response<BankDetailsInsertDTO> response = Response.<BankDetailsInsertDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Address created successfully")
                .data(bankDetailsInsertDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
