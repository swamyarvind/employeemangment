package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDTO {
private Long id;
    
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    
    @Email(message = "Provide a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    
    @NotNull(message = "Department ID is required")
    private Long departmentId;
}
