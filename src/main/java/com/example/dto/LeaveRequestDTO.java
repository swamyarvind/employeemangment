package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LeaveRequestDTO {
	 @NotNull(message = "Employee ID is required")
	    private Long employeeId;
	    
	    @NotNull(message = "Start date is required")
	    private LocalDate startDate;
	    
	    @NotNull(message = "End date is required")
	    private LocalDate endDate;
}
