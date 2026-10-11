package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.Employee;
import com.example.entity.Leave;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.repository.LeaveRepository;

@Service
@Transactional(readOnly = true)
public class LeaveService {

	 @Autowired 
	    private LeaveRepository leaveRepository;
	    
	    @Autowired 
	    private EmployeeRepository employeeRepository;
	    // Apply for a Leave
	    @Transactional
	    public Leave applyLeave(LeaveRequestDTO dto) {
	        Employee employee = employeeRepository.findById(dto.getEmployeeId())
	                .orElseThrow(() -> new ResourceNotFoundException("Employee mapping not found with ID: " + dto.getEmployeeId()));

	        // Business Logic Validation: Ensure start date is before end date
	        if (dto.getStartDate().isAfter(dto.getEndDate())) {
	            throw new IllegalArgumentException("Leave start date cannot be after the end date.");
	        }
	        Leave leave = new Leave();
	        leave.setStartDate(dto.getStartDate());
	        leave.setEndDate(dto.getEndDate());
	        leave.setEmployee(employee);
	        leave.setStatus(Leave.LeaveStatus.PENDING);

	        return leaveRepository.save(leave);
	    }
	    @Transactional
	    public Leave updateLeaveStatus(Long leaveId, Leave.LeaveStatus status) {
	        Leave leave = leaveRepository.findById(leaveId)
	                .orElseThrow(() -> new ResourceNotFoundException("Leave request entry reference not found with ID: " + leaveId));

	        leave.setStatus(status);
	        return leaveRepository.save(leave);
	    }

	    // Retrieve active leave requests for a single worker
	    public List<Leave> getLeavesByEmployee(Long employeeId) {
	        if (!employeeRepository.existsById(employeeId)) {
	            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
	        }
	        return leaveRepository.findByEmployeeId(employeeId);
	    }
}
