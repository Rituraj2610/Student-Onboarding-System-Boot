package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.TechStackInsertionDTO;
import com.rituraj.candidateOnboardingSystem.model.TechStack;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechStackMapper {
    TechStack toEntity(TechStackInsertionDTO dto);
    TechStackInsertionDTO toDto(TechStack stack);
}
