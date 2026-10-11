package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.Employee;
import com.example.entity.Leave;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.repository.LeaveRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

	  @Autowired private LeaveRepository leaveRepository;
	    @Autowired private EmployeeRepository employeeRepository;
	    @PostMapping("/apply")
	    public ResponseEntity<Leave> applyLeave(@Valid @RequestBody LeaveRequestDTO dto) {
	        Employee emp = employeeRepository.findById(dto.getEmployeeId())
	                .orElseThrow(() -> new ResourceNotFoundException("Employee mapping not found"));
	        
	        Leave leave = new Leave();
	        leave.setStartDate(dto.getStartDate());
	        leave.setEndDate(dto.getEndDate());
	        leave.setEmployee(emp);
	        leave.setStatus(Leave.LeaveStatus.PENDING);
	        
	        return ResponseEntity.ok(leaveRepository.save(leave));
	    }
	    
	    @PatchMapping("/{id}/status")
	    public ResponseEntity<Leave> updateLeaveStatus(
	            @PathVariable Long id, 
	            @RequestParam Leave.LeaveStatus status) {
	        
	        Leave leave = leaveRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Leave request entry reference not found"));
	        
	        leave.setStatus(status);
	        return ResponseEntity.ok(leaveRepository.save(leave));
	    }
}
