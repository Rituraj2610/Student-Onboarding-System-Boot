package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.DocumentInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    Document toEntity(DocumentInsertDTO dto);
    DocumentInsertDTO toDto(Document document);
}
