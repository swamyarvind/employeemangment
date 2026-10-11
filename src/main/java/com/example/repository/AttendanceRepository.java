package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>
{
	 @Query("SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate AND a.employee.id = :employeeId")
	    List<Attendance> searchAttendance(
	            @Param("employeeId") Long employeeId,
	            @Param("startDate") LocalDate startDate,
	            @Param("endDate") LocalDate endDate
	    );
}
