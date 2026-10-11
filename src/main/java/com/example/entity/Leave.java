package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leaves")
@Getter @Setter
public class Leave {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  @Column(nullable = false)
	    private LocalDate startDate;

	    @Column(nullable = false)
	    private LocalDate endDate;

	    @Enumerated(EnumType.STRING)
	    private LeaveStatus status = LeaveStatus.PENDING;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "employee_id", nullable = false)
	    private Employee employee;
	    public enum LeaveStatus {
	        PENDING, APPROVED, REJECTED
	    }
}
