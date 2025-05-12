package com.siscon.employee.infrastructure.repository;

import com.siscon.employee.infrastructure.database.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
