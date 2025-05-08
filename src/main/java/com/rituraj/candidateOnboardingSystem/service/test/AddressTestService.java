package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.AddressAddDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.mapper.AddressMapper;
import com.rituraj.candidateOnboardingSystem.model.Address;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.repo.AddressRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
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
            // THROW EXC
        }

        Response<Address> response = Response.<Address>builder()
                .status(ApiStatus.FOUND)
                .message("Address found successfully!")
                .data(optionalAddress.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<AddressAddDTO>> addAddressByCandidateId(AddressAddDTO addressAddDTO) {
        Candidate candidate = candidateTestService.getCandidateById2(addressAddDTO.getCandidateId());

        Address address = addressMapper.toEntity(addressAddDTO);
        address.setCandidate(candidate);

        // COULD ADD EXCEPTION IF ADDRESS MATCHES

        // we wont be saving in address but in candidate instead bec we use cascade type all
//        addressRepo.save(address);
        candidate.getAddressList().add(address);

        if(!candidateTestService.addCandidate2(candidate)){
            // THROW EXC
        }


        Response<AddressAddDTO> response = Response.<AddressAddDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Address created successfully")
                .data(addressAddDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
