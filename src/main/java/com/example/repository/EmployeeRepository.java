package com.example.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.email = :email")
    Optional<Employee> findByEmailWithDepartment(@Param("email") String email);

    // Standard pagination interface
    Page<Employee> findAll(Pageable pageable);
}
