package com.rituraj.candidateOnboardingSystem.dto;
import com.rituraj.candidateOnboardingSystem.enums.TechName;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CandidateRabbitDTO {
    private String name;
    private String email;
    private List<TechName> techNames; // Simplified tech stack


        // Constructor for JPQL
        public CandidateRabbitDTO(String name, String email, String techNames) {
            this.name = name;
            this.email = email;
            this.techNames = techNames == null || techNames.isEmpty() ?
                    Collections.emptyList() :
                    Arrays.stream(techNames.split(","))
                            .map(String::trim)
                            .map(TechName::valueOf)
                            .collect(Collectors.toList());
        }
    }




