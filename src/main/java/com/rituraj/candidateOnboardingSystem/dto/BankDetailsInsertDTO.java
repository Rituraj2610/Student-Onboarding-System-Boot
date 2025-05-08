package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.model.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDetailsInsertDTO {

    @NotNull(message = "Candidate id cant be null")
    private Long candidateId;

    @NotBlank(message = "Bank Name cant be empty")
    private String bankName;

    @NotBlank(message = "Account number cant be empty")
    @Pattern(regexp = "^[0-9]{10,16}$")
    private String accountNumber;

    @NotBlank(message = "IFSC number cant be empty")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code (e.g., SBIN0123456)")
    private String ifscCode;
}
