package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter @Setter
public class Employee {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "First name is required")
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    private String lastName;

	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    @Column(nullable = false, unique = true)
	    private String email;
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "department_id", nullable = false)
	    private Department department;

	    // Relationships: Employee -> Attendance/Leave/Reviews (OneToMany)
	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Attendance> attendances = new ArrayList<>();

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Leave> leaves = new ArrayList<>();

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<PerformanceReview> performanceReviews = new ArrayList<>();
}
