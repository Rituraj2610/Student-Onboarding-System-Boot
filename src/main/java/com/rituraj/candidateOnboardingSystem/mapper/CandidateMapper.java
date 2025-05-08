package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.CandidateInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "educationDetailsList", ignore = true)
    @Mapping(target = "documentList", ignore = true)
    @Mapping(target = "addressList", ignore = true)
    @Mapping(target = "bankDetailsList", ignore = true)
    @Mapping(target = "statusList", ignore = true)
    @Mapping(target = "techStackList", ignore = true)
    Candidate toEntity(CandidateInsertDTO dto);
    CandidateInsertDTO toDto(Candidate candidate);
}
