package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.JobInsertionDTO;
import com.rituraj.candidateOnboardingSystem.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "techStackList", ignore = true)
    Job toEntity(JobInsertionDTO dto);
    JobInsertionDTO toDto(Job job);
}
