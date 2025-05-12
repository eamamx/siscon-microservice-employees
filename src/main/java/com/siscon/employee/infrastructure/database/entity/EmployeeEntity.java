package com.siscon.employee.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;
    private String middleName;

    @Column(name = "lastName", nullable = false)
    private String lastName;
    private String secondLastName;
    private int age;
    private String gender;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String position;
}
