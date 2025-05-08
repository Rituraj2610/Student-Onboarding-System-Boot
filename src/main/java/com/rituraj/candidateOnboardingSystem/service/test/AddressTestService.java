package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.AddressAddDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.DuplicateEntityException;
import com.rituraj.candidateOnboardingSystem.exception.EntityInsertException;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.AddressMapper;
import com.rituraj.candidateOnboardingSystem.model.Address;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.repo.AddressRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AddressTestService {

    private AddressRepo addressRepo;
    private CandidateTestService candidateTestService;
    private AddressMapper addressMapper;

    public AddressTestService(AddressRepo addressRepo, CandidateTestService candidateTestService, AddressMapper addressMapper) {
        this.addressRepo = addressRepo;
        this.candidateTestService = candidateTestService;
        this.addressMapper = addressMapper;
    }

    public ResponseEntity<Response<Address>> getAddressByCandidateId(Long id) {
        Optional<Address> optionalAddress = addressRepo.findByCandidateId(id);
        if(optionalAddress.isEmpty()){
            throw new EntityNotFoundException("Address not found with provided id!");
        }

        Response<Address> response = Response.<Address>builder()
                .status(ApiStatus.FOUND)
                .message("Address found successfully!")
                .data(optionalAddress.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<AddressAddDTO>> addAddressByCandidateId(AddressAddDTO addressAddDTO) {
        Optional<Address> optionalAddress = addressRepo.findByStreetAddressAndZipAndType(addressAddDTO.getStreetAddress(), addressAddDTO.getZip(), addressAddDTO.getType());
        if(optionalAddress.isPresent()){
            throw new DuplicateEntityException("Duplicate address not allowed!");
        }

        Candidate candidate = candidateTestService.getCandidateById2(addressAddDTO.getCandidateId());
        Address address = addressMapper.toEntity(addressAddDTO);
        address.setCandidate(candidate);

        // we wont be saving in address but in candidate instead bec we use cascade type all
        // addressRepo.save(address);
        candidate.getAddressList().add(address);

        if(!candidateTestService.addCandidate2(candidate)){
            throw new EntityInsertException("Failed to persist candidate!");
        }

        Response<AddressAddDTO> response = Response.<AddressAddDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Address created successfully")
                .data(addressAddDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
