package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.EmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

	  @Autowired 
	    private EmployeeRepository employeeRepository;
	    
	    @Autowired 
	    private DepartmentRepository departmentRepository;
	    @Transactional
	    public Employee createEmployee(EmployeeDTO dto) {
	        Department department = departmentRepository.findById(dto.getDepartmentId())
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));

	        Employee employee = new Employee();
	        employee.setFirstName(dto.getFirstName());
	        employee.setLastName(dto.getLastName());
	        employee.setEmail(dto.getEmail());
	        employee.setDepartment(department);

	        return employeeRepository.save(employee);
	    }
	    public Page<Employee> getAllEmployees(Pageable pageable) {
	        return employeeRepository.findAll(pageable);
	    }
	    public Employee getEmployeeById(Long id) {
	        return employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	    }
	    @Transactional
	    public Employee updateEmployee(Long id, EmployeeDTO dto) {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	        
	        Department department = departmentRepository.findById(dto.getDepartmentId())
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));

	        employee.setFirstName(dto.getFirstName());
	        employee.setLastName(dto.getLastName());
	        employee.setEmail(dto.getEmail());
	        employee.setDepartment(department);

	        return employeeRepository.save(employee);
	    }
	    @Transactional
	    public void deleteEmployee(Long id) {
	        Employee employee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	        employeeRepository.delete(employee);
	    }
}
