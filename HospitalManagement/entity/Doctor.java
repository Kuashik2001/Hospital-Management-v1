package com.hm.HospitalManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String specialization;
    
    @Column(nullable = false, unique=true)
    private String email;
    
    @Column(nullable = false)
    private LocalDate dateOfJoining;
    
    @Column(nullable = false)
    private int experience;
    
    @OneToMany(mappedBy="doctor", cascade=CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    // Constructors
    public Doctor() {}

    public Doctor(String name, String specialization, LocalDate dateOfJoining, int experience, String email ) {
        this.name = name;
        this.specialization = specialization;
        this.dateOfJoining = dateOfJoining;
        this.experience = experience;
        this.email=email;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<Appointment> getAppointments()
   {
    	return appointments;
   }
   
    public void setAppointments(List<Appointment> appointments)
    {
    	this.appointments=appointments;
    }

	
    
}
