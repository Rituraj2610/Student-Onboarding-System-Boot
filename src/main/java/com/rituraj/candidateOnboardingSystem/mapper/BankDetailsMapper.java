package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.BankDetailsInsertDTO;
import com.rituraj.candidateOnboardingSystem.model.BankDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {
    BankDetails toEntity(BankDetailsInsertDTO dto);
    BankDetailsInsertDTO toDto(BankDetails bankDetails);
}
