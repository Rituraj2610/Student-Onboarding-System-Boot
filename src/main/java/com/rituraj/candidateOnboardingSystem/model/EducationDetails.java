package com.rituraj.candidateOnboardingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rituraj.candidateOnboardingSystem.enums.DegreeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "educational_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    @JsonBackReference
    private Candidate candidate;

    @NotBlank(message = "Degree name cannot be empty")
    @Column(name = "degree_name", nullable = false)
    private String degreeName;

    @NotBlank(message = "Institution name cannot be empty")
    @Column(name = "institution_name", nullable = false)
    private String institutionName;

    @NotNull(message = "Marks cannot be null")
    @DecimalMin(value = "0.0", message = "Marks cannot be negative")
    @DecimalMax(value = "100.0", message = "Marks cannot exceed 100")
    @Column(name = "marks_obtained")
    private Double marksObtained;

    @NotNull(message = "Year of passing cannot be null")
    @Min(value = 1900, message = "Year of passing must be after 1900")
    @Max(value = 9999, message = "Year of passing must be valid")
    @Column(name = "year_of_passing")
    private Integer yearOfPassing;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Degree type cannot be null")
    @Column(name = "degree_type", nullable = false)
    private DegreeType degreeType;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

}