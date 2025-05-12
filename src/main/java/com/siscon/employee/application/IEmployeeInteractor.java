package com.siscon.employee.application;

import com.siscon.employee.domain.model.Employee;

import java.util.List;

public interface IEmployeeInteractor {
    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    void deleteEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee request);

}
