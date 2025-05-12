package com.siscon.employee.infrastructure.mapper;

import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.infrastructure.rest.dto.EmployeeRequestDTO;
import com.siscon.employee.infrastructure.rest.dto.EmployeeResponseDTO;
import com.siscon.employee.infrastructure.rest.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {

    final private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void mapDtoToEntity() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "Eduardo", "Antonio", "Morales", "Avila", 42, "M", "10-05-1990", "developer"
        );

        Employee employee = mapper.toEmployee(dto);

        assertEquals("Eduardo", employee.getFirstName());
        assertEquals(LocalDate.of(1990, 5, 10), employee.getBirthDate());
    }

    @Test
    void entityToResponseDto() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Carlos");
        employee.setMiddleName("Luis");
        employee.setLastName("Martinez");
        employee.setSecondLastName("Martinez");
        employee.setBirthDate(LocalDate.of(1990, 8, 22));
        employee.setAge(33);
        employee.setGender("M");
        employee.setPosition("QA");

        EmployeeResponseDTO dto = mapper.toDto(employee);

        assertEquals("Carlos", dto.firstName());
        assertEquals("22-08-1990", dto.birthDate());
    }
}
