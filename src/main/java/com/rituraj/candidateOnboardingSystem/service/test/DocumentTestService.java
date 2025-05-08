package com.rituraj.candidateOnboardingSystem.service.test;

import com.rituraj.candidateOnboardingSystem.dto.DocumentInsertDTO;
import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import com.rituraj.candidateOnboardingSystem.exception.EntityNotFoundException;
import com.rituraj.candidateOnboardingSystem.mapper.DocumentMapper;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Document;
import com.rituraj.candidateOnboardingSystem.dto.Response;
import com.rituraj.candidateOnboardingSystem.repo.DocumentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DocumentTestService {
    private DocumentRepo documentRepo;
    private CandidateTestService candidateTestService;
    private DocumentMapper documentMapper;

    public DocumentTestService(DocumentRepo documentRepo, DocumentMapper documentMapper, CandidateTestService candidateTestService) {
        this.documentRepo = documentRepo;
        this.documentMapper = documentMapper;
        this.candidateTestService = candidateTestService;
    }

    public ResponseEntity<Response<Document>> getDocumentByCandidateId(Long id) {
        Optional<Document> optionalDocument = documentRepo.findByCandidateId(id);
        if(optionalDocument.isEmpty()){
            throw new EntityNotFoundException("Document not found with provided id!");
        }

        Response<Document> response = Response.<Document>builder()
                .status(ApiStatus.FOUND)
                .message("Document found successfully!")
                .data(optionalDocument.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    public ResponseEntity<Response<DocumentInsertDTO>> addDocumentByCandidateId(DocumentInsertDTO documentInsertDTO) {
        Candidate candidate = candidateTestService.getCandidateById2(documentInsertDTO.getCandidateId());

        Document document = documentMapper.toEntity(documentInsertDTO);
        document.setCandidate(candidate);
        candidate.getDocumentList().add(document);
        // COULD ADD EXCEPTION IF ADDRESS MATCHES
//        documentRepo.save(document);

        if(!candidateTestService.addCandidate2(candidate)){
            // THROW
        }

        Response<DocumentInsertDTO> response = Response.<DocumentInsertDTO>builder()
                .status(ApiStatus.CREATED)
                .message("Document created successfully")
                .data(documentInsertDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
