package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.DocumentStatus;
import com.rituraj.candidateOnboardingSystem.enums.DocumentType;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentInsertDTO {

    @NotNull(message = "Candidate id cant be null")
    private long candidateId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document type cant be null")
    private DocumentType documentType;

    @NotBlank(message = "Path cant be empty")
    private String filePath;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document status cant be null")
    private DocumentStatus documentStatus;
}
