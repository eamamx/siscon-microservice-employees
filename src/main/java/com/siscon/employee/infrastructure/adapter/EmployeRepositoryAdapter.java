package com.siscon.employee.infrastructure.adapter;

import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.domain.repository.EmployeeRepository;
import com.siscon.employee.infrastructure.database.entity.EmployeeEntity;
import com.siscon.employee.infrastructure.database.mapper.EmployeeEntityMapper;
import com.siscon.employee.infrastructure.repository.JpaEmployeeRepository;
import com.siscon.employee.infrastructure.rest.exception.DataException;
import com.siscon.employee.infrastructure.rest.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmployeRepositoryAdapter implements EmployeeRepository {

    public static final String EMPLOYEE_NOT_FOUND = "Employee not found id:";
    public static final String ERROR = "Error";
    private final JpaEmployeeRepository jpaEmployeeRepository;
    private final EmployeeEntityMapper mapper;

    @Override
    public List<Employee> getAllEmployees() {
        log.info("get all employees");
        return jpaEmployeeRepository.findAll().stream()
                .map(employeeEntity -> mapper.fromEntityToEmployee(employeeEntity)).collect(Collectors.toList());
    }

    @Override
    public Employee findEmployeeById(Long id) {
        log.info("find employee by id {}", id);
        EmployeeEntity employeeEntity = jpaEmployeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        return mapper.fromEntityToEmployee(employeeEntity);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        log.info("save employee {}", employee.toString());
        EmployeeEntity employeeEntityIn = mapper.fromEmployeeToEntity(employee);
        EmployeeEntity employeeEntityOut = jpaEmployeeRepository.save(employeeEntityIn);
        return mapper.fromEntityToEmployee(employeeEntityOut);
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        log.info("Saving list of {} employees", employees.size());
        List<EmployeeEntity> employeesEntity = jpaEmployeeRepository.saveAll(mapper.fromListEmployetoListEntity(employees));
        return mapper.fromListEntitytoListEmploye(employeesEntity);

    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        log.info("update employee {}", updatedEmployee.toString());

        EmployeeEntity existingEntity = jpaEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + id));
        existingEntity.setFirstName(updatedEmployee.getFirstName());
        existingEntity.setMiddleName(updatedEmployee.getMiddleName());
        existingEntity.setLastName(updatedEmployee.getLastName());
        existingEntity.setSecondLastName(updatedEmployee.getSecondLastName());
        existingEntity.setAge(updatedEmployee.getAge());
        existingEntity.setGender(updatedEmployee.getGender());
        existingEntity.setBirthDate(updatedEmployee.getBirthDate()); // LocalDate
        existingEntity.setPosition(updatedEmployee.getPosition());

        EmployeeEntity savedEntity = jpaEmployeeRepository.save(existingEntity);
        return mapper.fromEntityToEmployee(savedEntity);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        log.info("delete employee {}", id);
        Optional<EmployeeEntity> employeEntity = jpaEmployeeRepository.findById(id);
        jpaEmployeeRepository.delete(employeEntity.orElseThrow(() -> new DataException(ERROR)));
    }
}
