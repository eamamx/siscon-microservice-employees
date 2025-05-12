package com.siscon.employee.infrastructure.rest.mapper;

import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.infrastructure.rest.dto.EmployeeRequestDTO;
import com.siscon.employee.infrastructure.rest.dto.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    Employee toEmployee(EmployeeRequestDTO dto);

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    EmployeeResponseDTO toDto(Employee employee);

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    EmployeeRequestDTO toDtoRequest(Employee employee);

    List<EmployeeResponseDTO> toDtoList(List<Employee> list);

    List<Employee> toEmployeeList(List<EmployeeRequestDTO> employees);
}
