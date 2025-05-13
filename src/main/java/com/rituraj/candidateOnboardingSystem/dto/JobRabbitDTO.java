package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.DegreeType;
import com.rituraj.candidateOnboardingSystem.enums.JobRole;
import com.rituraj.candidateOnboardingSystem.enums.JobStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class JobRabbitDTO {

    private Long id;
    private BigDecimal ctc;
    private JobRole jobRole;
    private DegreeType degreeType;
    private Double minMarks;
    private Boolean activeStatus;
    // techStackList excluded for email simplicity
}
