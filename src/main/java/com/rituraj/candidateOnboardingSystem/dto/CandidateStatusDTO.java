package com.rituraj.candidateOnboardingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateStatusDTO {
    private Long candidateId;
    private Long statusId;
}
