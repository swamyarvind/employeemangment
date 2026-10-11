package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter @Setter
public class Department {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 @NotBlank(message = "Department name is required")
	    @Column(nullable = false, unique = true)
	    private String name;
	 @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Employee> employees = new ArrayList<>();

}
