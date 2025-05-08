package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.AddressType;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressAddDTO {

    @NotNull(message = "Candidate id cant be null")
    private Long candidateId;

    @NotBlank(message = "Street address cannot be empty")
    private String streetAddress;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "State cannot be empty")
    private String state;

    @NotBlank(message = "Zip cannot be empty")
    @Pattern(regexp = "^[0-9]{5}(-[0-9]{4})?$", message = "Invalid ZIP code format")
    private String zip;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Address type cannot be null")
    private AddressType type;
}
