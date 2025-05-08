package com.rituraj.candidateOnboardingSystem.controller.test;

import com.rituraj.candidateOnboardingSystem.dto.AddressAddDTO;
import com.rituraj.candidateOnboardingSystem.model.Address;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.service.test.AddressTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/address")
public class AdressTestController {

    private AddressTestService addressTestService;

    public AdressTestController(AddressTestService addressTestService) {
        this.addressTestService = addressTestService;
    }

    /*
     * Method Name: getAddressByCandidateId(@PathVariable Long id)
     * Usage: Fetches the address by candidate id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<Address>> getAddressByCandidateId(@PathVariable Long id){
        return addressTestService.getAddressByCandidateId(id);
    }

    /*
     * Method Name: addAddressByCandidateId(@Valid @RequestBody AddressAddDTO addressAddDTO)
     * Usage: Inserts address using candidate id
     */
    @PostMapping
    public ResponseEntity<Response<AddressAddDTO>> addAddressByCandidateId(@Valid @RequestBody AddressAddDTO addressAddDTO){
        return addressTestService.addAddressByCandidateId(addressAddDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<> updateAddressByCandidateId(@PathVariable Long id){
//        return addressTestService.updateAddressByCandidateId(id);
//    }
}
