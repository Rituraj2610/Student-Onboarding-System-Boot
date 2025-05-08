package com.rituraj.candidateOnboardingSystem.model;

import com.rituraj.candidateOnboardingSystem.enums.ApiStatus;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
public class Response<T> {

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Status cant be empty")
    private ApiStatus status;

    @NotBlank(message = "Model cant be empty")
    private String message;

    @NotNull(message = "Data cant be null")
    private T data;

    @NotNull(message = "Date cant be null")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
