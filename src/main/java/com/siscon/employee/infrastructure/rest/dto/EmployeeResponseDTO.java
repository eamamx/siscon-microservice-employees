package com.siscon.employee.infrastructure.rest.dto;

import lombok.Builder;

@Builder
public record EmployeeResponseDTO(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        int age,
        String gender,
        String birthDate,
        String position
) {}