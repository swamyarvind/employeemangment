package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	  @Autowired private EmployeeRepository employeeRepository;
	    @Autowired private DepartmentRepository departmentRepository;

	    @PostMapping
	    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
	        Department dept = departmentRepository.findById(dto.getDepartmentId())
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
	        
	        Employee emp = new Employee();
	        emp.setFirstName(dto.getFirstName());
	        emp.setLastName(dto.getLastName());
	        emp.setEmail(dto.getEmail());
	        emp.setDepartment(dept);
	        
	        return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.CREATED);
	    }
	    @GetMapping
	    public ResponseEntity<Page<Employee>> getAllEmployees(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return ResponseEntity.ok(employeeRepository.findAll(pageable));
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	        Employee emp = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	        return ResponseEntity.ok(emp);
}
	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
	        Employee emp = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	        Department dept = departmentRepository.findById(dto.getDepartmentId())
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
	        
	        emp.setFirstName(dto.getFirstName());
	        emp.setLastName(dto.getLastName());
	        emp.setEmail(dto.getEmail());
	        emp.setDepartment(dept);
	        
	        return ResponseEntity.ok(employeeRepository.save(emp));
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        Employee emp = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	        employeeRepository.delete(emp);
	        return ResponseEntity.noContent().build();
	    }
}
