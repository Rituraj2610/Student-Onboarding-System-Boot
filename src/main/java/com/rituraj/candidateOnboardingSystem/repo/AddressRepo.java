package com.rituraj.candidateOnboardingSystem.repo;

import com.rituraj.candidateOnboardingSystem.enums.AddressType;
import com.rituraj.candidateOnboardingSystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.candidate.id = :id")
    Optional<Address> findByCandidateId(Long id);

    @Query("SELECT a FROM Address a WHERE a.streetAddress = :streetAddress AND a.zip = :zip AND a.type = :type")
    Optional<Address> findByStreetAddressAndZipAndType(@Param("streetAddress") String streetAddress, @Param("zip") String zip, @Param("type") AddressType type);
}
