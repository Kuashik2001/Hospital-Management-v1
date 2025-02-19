package com.hm.HospitalManagement.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Transactional
@Entity
@Table(name = "patients")
@Component
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate unique ID
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false, unique = true)
	private String mobileNo;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String guardianName;

	// @Column(nullable = false)
	private String bloodGroup;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;

	// Constructors
	public Patient() {
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//
	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
