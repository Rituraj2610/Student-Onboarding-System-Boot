package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.TechName;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Job;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechStackInsertionDTO {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tech stack name cannot be blank")
    private TechName name;
}
