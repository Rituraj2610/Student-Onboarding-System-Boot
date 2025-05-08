package com.rituraj.candidateOnboardingSystem.controller.test;

import com.rituraj.candidateOnboardingSystem.dto.DocumentInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.Document;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.service.test.DocumentTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/document")
public class DocumentTestController {
    private DocumentTestService documentTestService;

    public DocumentTestController(DocumentTestService documentTestService) {
        this.documentTestService = documentTestService;
    }

    /*
     * Method Name: getDocumentByCandidateId(@PathVariable Long id)
     * Usage: Fetch document by candidate id from DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<Document>> getDocumentByCandidateId(@PathVariable Long id){
        return documentTestService.getDocumentByCandidateId(id);
    }

    /*
     * Method Name: addDocumentByCandidateId(@Valid @RequestBody DocumentInsertDTO documentInsertDTO)
     * Usage: Inserts document by caniddate id in the DB
     */
    @PostMapping
    public ResponseEntity<Response<DocumentInsertDTO>> addDocumentByCandidateId(@Valid @RequestBody DocumentInsertDTO documentInsertDTO){
        return documentTestService.addDocumentByCandidateId(documentInsertDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<> updateDocumentByCandidateId(@PathVariable Long id){
//        return documentTestService.updateDocumentByCandidateId(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<> deleteDocumentByCandidateId(@PathVariable Long id){
//        return documentTestService.deleteDocumentByCandidateId(id);
//    }
}

