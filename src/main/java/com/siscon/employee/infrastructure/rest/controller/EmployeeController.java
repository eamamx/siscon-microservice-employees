package com.siscon.employee.infrastructure.rest.controller;

import com.siscon.employee.application.EmployeeInteractorImpl;
import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.infrastructure.rest.dto.EmployeeRequestDTO;
import com.siscon.employee.infrastructure.rest.dto.EmployeeResponseDTO;
import com.siscon.employee.infrastructure.rest.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Employees CRUD")
public class EmployeeController {
    public static final String INTERNAL_ERROR = "Internal Error";
    private final EmployeeInteractorImpl employeeService;
    private final EmployeeMapper mapper;

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(mapper.toDto(employee));
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(mapper.toDtoList(employees));
    }

    @Operation(summary = "Create one employee")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmploye(@RequestBody @Valid EmployeeRequestDTO employee) {
        Employee employeeToSave = mapper.toEmployee(employee);
        Employee savedEmployee = employeeService.saveEmployee(employeeToSave);
        EmployeeResponseDTO responseDTO = mapper.toDto(savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Create one or more employees")
    @PostMapping("all")
    public ResponseEntity<List<EmployeeResponseDTO>> saveAll(@RequestBody @Valid List< EmployeeRequestDTO> employees) {
        List<Employee> employeesToSave = mapper.toEmployeeList(employees);
        List<Employee> savedEmployees = employeeService.saveEmployees(employeesToSave);
        List<EmployeeResponseDTO> responseDTOs = mapper.toDtoList(savedEmployees);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTOs);
    }

    @Operation(summary = "Update employee by ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EmployeeRequestDTO request) {
        return ResponseEntity.ok(mapper.toDto(employeeService.updateEmployee(id, mapper.toEmployee(request))));
    }

    @Operation(summary = "Delete employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
