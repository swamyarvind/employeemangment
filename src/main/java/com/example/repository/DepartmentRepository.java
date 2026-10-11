package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>
{
	 Optional<Department> findByName(String name);

	    /**
	     * JPQL performance optimization: Fetches the department and pre-loads 
	     * its associated collection of employees in a single database round-trip.
	     */
	    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.id = :id")
	    Optional<Department> findByIdWithEmployees(@Param("id") Long id);

}
