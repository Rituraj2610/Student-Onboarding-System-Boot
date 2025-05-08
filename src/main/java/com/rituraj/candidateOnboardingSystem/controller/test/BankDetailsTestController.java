package com.rituraj.candidateOnboardingSystem.controller.test;

import com.rituraj.candidateOnboardingSystem.dto.BankDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.BankDetails;
import com.rituraj.candidateOnboardingSystem.model.Response;
import com.rituraj.candidateOnboardingSystem.service.test.BankDetailsTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/bankdetails")
public class BankDetailsTestController {
    private BankDetailsTestService bankDetailsTestService;

    public BankDetailsTestController(BankDetailsTestService bankDetailsTestService) {
        this.bankDetailsTestService = bankDetailsTestService;
    }

    /*
     * Method Name: getBankDetailsByCandidateId(@PathVariable Long id)
     * Usage: Fetches bank details by candidate id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<BankDetails>> getBankDetailsByCandidateId(@PathVariable Long id){
        return bankDetailsTestService.getBankDetailsByCandidateId(id);
    }


    /*
     * Method Name: addBankDetailsByCandidateId(@Valid @RequestBody BankDetailsInsertDTO bankDetailsInsertDTO)
     * Usage: Insert Bank details in the DB
     */
    @PostMapping("/")
    public ResponseEntity<Response<BankDetailsInsertDTO>> addBankDetailsByCandidateId(@Valid @RequestBody BankDetailsInsertDTO bankDetailsInsertDTO){
        return bankDetailsTestService.addBankDetailsByCandidateId(bankDetailsInsertDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<> updateBankDetailsByCandidateId(@PathVariable Long id){
//        return bankDetailsService.updateBankDetailsByCandidateId(id);
//    }
}


