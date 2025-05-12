package com.siscon.employee.domain.repository;

import com.siscon.employee.domain.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployeeById(Long id);


}
