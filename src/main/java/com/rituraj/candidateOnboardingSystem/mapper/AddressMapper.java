package com.rituraj.candidateOnboardingSystem.mapper;

import com.rituraj.candidateOnboardingSystem.dto.AddressAddDTO;
import com.rituraj.candidateOnboardingSystem.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressAddDTO dto);
    AddressAddDTO toDto(Address address);
}
