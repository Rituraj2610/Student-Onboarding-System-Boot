package com.rituraj.candidateOnboardingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobStatusUpdateDTO {

    @NotNull(message = "Id cant be null")
    private Long id;

    @NotNull(message = "Status cant be null")
    private Boolean activeStatus;
}
