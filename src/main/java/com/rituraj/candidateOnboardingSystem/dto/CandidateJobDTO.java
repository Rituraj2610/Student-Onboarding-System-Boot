package com.rituraj.candidateOnboardingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateJobDTO {

    @NotNull(message = "Candidate id cant be null")
    private Long candidateId;
    @NotNull(message = "Job id cant be null")
    private Long jobId;
}
