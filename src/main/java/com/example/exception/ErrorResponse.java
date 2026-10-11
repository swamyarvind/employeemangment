package com.example.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorResponse {
	private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors;

}
