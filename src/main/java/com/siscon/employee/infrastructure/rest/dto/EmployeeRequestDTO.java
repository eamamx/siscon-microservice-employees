package com.siscon.employee.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequestDTO{
        @NotBlank(message = "First name is required")
        private String firstName;
        private String middleName;
        @NotBlank(message = "Last name is required")
        private String lastName;
        private String secondLastName;
        @NotNull(message = "Age is required")
        private int age;
        @NotBlank(message = "Gender is required")
        private String gender;
        @NotBlank(message = "Birth date is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private String birthDate;
        private String position;
}
