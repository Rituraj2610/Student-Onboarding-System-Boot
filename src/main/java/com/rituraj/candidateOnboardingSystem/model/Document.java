package com.rituraj.candidateOnboardingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rituraj.candidateOnboardingSystem.enums.DocumentStatus;
import com.rituraj.candidateOnboardingSystem.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document type cant be null")
    private DocumentType documentType;

    @Column(name = "file_path")
    @NotBlank(message = "Path cant be empty")
    private String filePath;

    @Column(name = "document_status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document status cant be null")
    private DocumentStatus documentStatus;

    @Column(name = "uploaded_at")
    @CreatedDate
    private LocalDateTime uploadedAt;

}
