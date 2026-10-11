package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>
{
	 // 1. Find all leave applications submitted by a specific employee
    List<Leave> findByEmployeeId(Long employeeId);

    // 2. Find leaves filtered by their operational status (e.g., PENDING, APPROVED)
    List<Leave> findByStatus(Leave.LeaveStatus status);

    /**
     * 3. JPQL Query: Fetch all approved leave slots intersecting with a specific date range.
     * Useful for checking schedule availability or workforce shortages.
     */
    @Query("SELECT l FROM Leave l WHERE l.status = 'APPROVED' AND " +
           "(l.startDate <= :endDate AND l.endDate >= :startDate)")
    List<Leave> findActiveLeavesInDateRange(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate
    );
}
