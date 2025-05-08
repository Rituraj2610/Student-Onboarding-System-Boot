package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.DegreeType;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDetailsInsertDTO {

    @NotNull(message = "Candidate id cant be null")
    private long candidateId;

    @NotBlank(message = "Degree name cannot be empty")
    private String degreeName;

    @NotBlank(message = "Institution name cannot be empty")
    private String institutionName;

    @NotNull(message = "Marks cannot be null")
    @DecimalMin(value = "0.0", message = "Marks cannot be negative")
    @DecimalMax(value = "100.0", message = "Marks cannot exceed 100")
    private Double marksObtained;

    @NotNull(message = "Year of passing cannot be null")
    @Min(value = 1900, message = "Year of passing must be after 1900")
    @Max(value = 9999, message = "Year of passing must be valid")
    private Integer yearOfPassing;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Degree type cannot be null")
    private DegreeType degreeType;


}
