package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.EducationDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.EducationDetails;
import com.rituraj.candidateOnboardingSystem.repo.EducationDetailsRepo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EducationDetailsMapper {
    EducationDetails toEntity(EducationDetailsInsertDTO dto);
    EducationDetailsInsertDTO toDto(EducationDetails educationDetails);
}
