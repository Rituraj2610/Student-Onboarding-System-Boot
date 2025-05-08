package com.rituraj.candidateOnboardingSystem.exception;

import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponse {
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Status cant be empty")
    private ApiStatus status;

    @NotBlank(message = "Model cant be empty")
    private String message;

    @NotNull(message = "Date cant be null")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
