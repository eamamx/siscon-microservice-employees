package com.siscon.employee.application;

import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeInteractorImpl implements IEmployeeInteractor {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {

        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee findEmployeeById(Long id) {

        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveEmployees(employees);
    }

    @Override
    public void deleteEmployeeById(Long id) {

        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        return employeeRepository.updateEmployee(id, employee);
    }
}