package com.rituraj.candidateOnboardingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rituraj.candidateOnboardingSystem.enums.TechName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name = "tech_stack")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tech stack name cannot be blank")
    @Column(nullable = false)
    private TechName name;

    @ManyToMany(mappedBy = "techStackList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Candidate> candidateList = new ArrayList<>();

    @ManyToMany(mappedBy = "techStackList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Job> jobList = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}