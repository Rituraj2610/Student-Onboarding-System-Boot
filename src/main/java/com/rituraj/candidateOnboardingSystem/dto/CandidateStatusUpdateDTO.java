package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.JobStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateStatusUpdateDTO {

    @NotNull(message = "Candidate Id cant be null")
    private Long candidateId;

    @NotNull(message = "Status Id cant be null")
    private Long statusId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job status can't be empty")
    private JobStatus jobStatus;
}
