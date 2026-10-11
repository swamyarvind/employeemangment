package com.example.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "attendances")
@Getter @Setter
public class Attendance {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 @Column(nullable = false)
	    private LocalDate date;

	    private LocalTime clockIn;
	    private LocalTime clockOut;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "employee_id", nullable = false)
	    private Employee employee;

}
