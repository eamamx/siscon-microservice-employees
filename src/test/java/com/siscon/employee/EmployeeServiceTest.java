package com.siscon.employee;

import com.siscon.employee.application.EmployeeInteractorImpl;
import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

	@Test
	void testGetAllEmployess(){
		EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
		when(employeeRepository.getAllEmployees()).thenReturn(Collections.singletonList(new Employee()));

		EmployeeInteractorImpl employeeService = new EmployeeInteractorImpl(employeeRepository);
		List<Employee> result = employeeService.getAllEmployees();
		assertEquals(1, result.size());
		verify(employeeRepository,times(1)).getAllEmployees();
	}
}
