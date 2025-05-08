package com.rituraj.candidateOnboardingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rituraj.candidateOnboardingSystem.enums.DegreeType;
import com.rituraj.candidateOnboardingSystem.enums.JobRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "CTC cannot be null")
    @DecimalMin(value = "0.0", message = "CTC cannot be negative")
    @Column(nullable = false)
    private BigDecimal ctc;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job role cannot be null")
    @Column(name = "job_role", nullable = false)
    private JobRole jobRole;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Degree type cannot be null")
    @Column(name = "degree_type", nullable = false)
    private DegreeType degreeType;

    @NotNull(message = "Minimum marks cannot be null")
    @DecimalMin(value = "60.0", message = "Minimum marks cannot be less than 60")
    @DecimalMax(value = "100.0", message = "Minimum marks cannot exceed 100")
    @Column(name = "minimum_marks", nullable = false)
    private Double minMarks;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_tech_stack",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_stack_id")
    )
    @JsonManagedReference
    private List<TechStack> techStackList = new ArrayList<>();

    @Column(name = "active_status")
    @NotNull(message = "Active status cant be null")
    @Builder.Default
    private Boolean activeStatus = true;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

}