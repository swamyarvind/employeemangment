package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "performance_reviews")
@Getter @Setter
public class PerformanceReview {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	   @Column(nullable = false)
	    private LocalDate reviewDate;

	    @Min(1) @Max(5)
	    private int score;

	    private String feedback;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "employee_id", nullable = false)
	    private Employee employee;
}
