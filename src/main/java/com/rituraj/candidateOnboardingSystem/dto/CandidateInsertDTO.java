package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.TechName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateInsertDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number (10-15 digits, optional +)")
    private String phone;

    @NotEmpty(message = "Tech names cannot be empty")
    private List<TechName> techNames;

}