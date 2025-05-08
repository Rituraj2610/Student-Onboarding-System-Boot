package com.rituraj.candidateOnboardingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bank_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

    @NotBlank(message = "Bank Name cant be empty")
    private String bankName;

    @Column(name = "account_number")
    @NotBlank(message = "Account number cant be empty")
    @Pattern(regexp = "^[0-9]{10,16}$")
    private String accountNumber;

    @Column(name = "ifsc_code")
    @NotBlank(message = "IFSC number cant be empty")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code (e.g., SBIN0123456)")
    private String ifscCode;


}
