package com.siscon.employee.domain.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
