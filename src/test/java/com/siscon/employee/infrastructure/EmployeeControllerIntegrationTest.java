package com.siscon.employee.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siscon.employee.domain.model.Employee;
import com.siscon.employee.domain.repository.EmployeeRepository;
import com.siscon.employee.infrastructure.database.entity.EmployeeEntity;
import com.siscon.employee.infrastructure.rest.dto.EmployeeRequestDTO;
import com.siscon.employee.infrastructure.rest.dto.EmployeeResponseDTO;
import com.siscon.employee.infrastructure.rest.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeMapper mapper;

    @BeforeEach
    void setUp() {

        repository.saveEmployee(Employee.builder()
                .firstName("Eduardo")
                .lastName("Morales")
                .age(41)
                .gender("M")
                .birthDate(LocalDate.of(1983, 10, 10))
                .position("developer")
                .build());
    }

    @Test
    @DisplayName("GET /api/employees returns list")
    void returnEmployeesList() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("GET /api/employees/{id} con ID inexistente devuelve 404")
    void returnNotFoundWhenEmployeeDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/employees/9999"))
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("GET /api/employees/{id} returns an employee")
    void returnEmployeeById() throws Exception {
        Employee entity = repository.getAllEmployees().get(0);
        Long id = entity.getId();

        mockMvc.perform(get("/api/employees/" + id))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.firstName", is("Eduardo")))
                .andExpect( jsonPath("$.lastName", is("Morales")));
    }

    @Test
    @DisplayName("POST /employees insert an employee")
    void iInsertEmployee() throws Exception {
        EmployeeResponseDTO dto = EmployeeResponseDTO.builder()
                .firstName("Ana")
                .lastName("Vivas")
                .age(38)
                .gender("F")
                .birthDate("20-04-1987")
                .position("Tester")
                .build();

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Ana")));
    }

    @Test
    @DisplayName("PUT /api/employees/{id} update an employee")
    void updateEmployee() throws Exception {
        Employee employee= repository.getAllEmployees().get(0);
        employee.setPosition("Team Lead");
        EmployeeRequestDTO request = mapper.toDtoRequest(employee);

        mockMvc.perform(put("/api/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position", is("Team Lead")));
    }

    @Test
    @DisplayName("DELETE /api/employees/{id} delete an employee")
    void deleteEmployee() throws Exception {
        var id = repository.getAllEmployees().get(0).getId();

        mockMvc.perform(delete("/api/employees/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("POST /employees/all insert an employee")
    void iInsertEmployeeAll() throws Exception {
        EmployeeResponseDTO dto = EmployeeResponseDTO.builder()
                .firstName("Ana")
                .lastName("Vivas")
                .age(38)
                .gender("F")
                .birthDate("20-04-1987")
                .position("Tester")
                .build();

        EmployeeResponseDTO dto2 = EmployeeResponseDTO.builder()
                .firstName("Eduardo")
                .lastName("Morales")
                .age(38)
                .gender("m")
                .birthDate("20-04-1987")
                .position("Otro")
                .build();

        List request = List.of(dto,dto2);
        mockMvc.perform(post("/api/employees/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

    }

}
