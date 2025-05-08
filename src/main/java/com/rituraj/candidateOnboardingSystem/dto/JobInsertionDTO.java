package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.DegreeType;
import com.rituraj.candidateOnboardingSystem.enums.JobRole;
import com.rituraj.candidateOnboardingSystem.enums.TechName;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobInsertionDTO {
    @NotNull(message = "CTC cannot be null")
    @DecimalMin(value = "0.0", message = "CTC cannot be negative")
    private BigDecimal ctc;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job role cannot be null")
    private JobRole jobRole;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Degree type cannot be null")
    private DegreeType degreeType;

    @NotNull(message = "Minimum marks cannot be null")
    @DecimalMin(value = "60.0", message = "Minimum marks cannot be less than 60")
    @DecimalMax(value = "100.0", message = "Minimum marks cannot exceed 100")
    private Double minMarks;

    @NotEmpty(message = "Tech names cannot be empty")
    private List<TechName> techNames;


}
