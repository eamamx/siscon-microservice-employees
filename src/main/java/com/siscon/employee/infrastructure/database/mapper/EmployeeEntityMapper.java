package com.siscon.employee.infrastructure.database.mapper;

import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.infrastructure.database.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper {

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    Employee fromEntityToEmployee(EmployeeEntity employeeEntity);

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    EmployeeEntity fromEmployeeToEntity(Employee employee);

    List<EmployeeEntity> fromListEmployetoListEntity(List<Employee> employees);

    List<Employee> fromListEntitytoListEmploye(List<EmployeeEntity> employees);
}
