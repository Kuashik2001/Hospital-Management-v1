//package com.hm.HospitalManagement.Entity;
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "appointments")
//public class Appointment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate unique ID
//    private Long id;
//
//    @Column(nullable = false)
//    private String patientName;
//
//    @Column(nullable = false)
//    private int age;
//
//    @Column(nullable = false)
//    private String bloodGroup;
//
//    @Column(nullable = false)
//    private String disease;
//
//    @Column(nullable = false)
//    private LocalDate appointmentDate;
//
//    @Column(nullable = false)
//    private String doctorName;
//
//    @Column(nullable = false)
//    private String doctorEmail;
//
//    @Column(nullable = false)
//    private String status = "Pending";  // Default status is "Pending"
//
//    // Constructors
//    public Appointment() {}
//
//    public Appointment(String patientName, int age, String bloodGroup, String disease, LocalDate appointmentDate, String doctorName, String doctorEmail) {
//        this.patientName = patientName;
//        this.age = age;
//        this.bloodGroup = bloodGroup;
//        this.disease = disease;
//        this.appointmentDate = appointmentDate;
//        this.doctorName = doctorName;
//        this.doctorEmail = doctorEmail;
//    }
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getPatientName() { return patientName; }
//    public void setPatientName(String patientName) { this.patientName = patientName; }
//
//    public int getAge() { return age; }
//    public void setAge(int age) { this.age = age; }
//
//    public String getBloodGroup() { return bloodGroup; }
//    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
//
//    public String getDisease() { return disease; }
//    public void setDisease(String disease) { this.disease = disease; }
//
//    public LocalDate getAppointmentDate() { return appointmentDate; }
//    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
//
//    public String getDoctorName() { return doctorName; }
//    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
//
//    public String getDoctorEmail() { return doctorEmail; }
//    public void setDoctorEmail(String doctorEmail) { this.doctorEmail = doctorEmail; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//}

// Working Earlier
//package com.hm.HospitalManagement.Entity;
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "appointments")
//public class Appointment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id", nullable = false)
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_id", nullable = false)
//    private Doctor doctor;
//
//    @Column(nullable = false)
//    private LocalDate appointmentDate;
//
//    @Column(nullable = false)
//    private String disease;
//
//    @Column(nullable = false)
//    private String status = "Pending";  // Default status is "Pending"
//
//    // Constructors
//    public Appointment() {}
//
//    public Appointment(Patient patient, Doctor doctor, LocalDate appointmentDate, String disease) {
//        this.patient = patient;
//        this.doctor = doctor;
//        this.appointmentDate = appointmentDate;
//        this.disease = disease;
//    }
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Patient getPatient() { return patient; }
//    public void setPatientName(Patient patient) { this.patient = patient; }
//
//    public Doctor getDoctor() { return doctor; }
//    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
//
//    public LocalDate getAppointmentDate() { return appointmentDate; }
//    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
//
//    public String getDisease() { return disease; }
//    public void setDisease(String disease) { this.disease = disease; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//}


//Added

package com.hm.HospitalManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(nullable = false)
    private String disease;
    
    //private String bloodGroup;
    
    @Column(nullable = false)
    private int age;
    
    @Column(nullable = false)
    private String gender;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    
   

    @Column(nullable = false)
    private LocalDate appointmentDate;
    @Column(nullable = false)
    private String status; // "Pending", "Approved", "Rejected"

    // Constructors
    public Appointment() {}

    public Appointment(Patient patient,Doctor doctor,  String disease, LocalDate appointmentDate, String status) {
        this.doctor = doctor;
        this.patient = patient;
        this.disease = disease;
        this.age = age;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
//
//    public String getPatientName() {
//        return patientName;
//    }
//
//    public void setPatientName(String patientName) {
//        this.patientName = patientName;
//    }
//
//    public String getBloodGroup() {
//        return bloodGroup;
//    }
//
//    public void setBloodGroup(String bloodGroup) {
//        this.bloodGroup = bloodGroup;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getDisease() {
//        return disease;
//    }
//
//    public void setDisease(String disease) {
//        this.disease = disease;
//    }
//
//    public LocalDate getAppointmentDate() {
//        return appointmentDate;
//    }
//
//    public void setAppointmentDate(LocalDate appointmentDate) {
//        this.appointmentDate = appointmentDate;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
    
 // Getters and Setters
    
}