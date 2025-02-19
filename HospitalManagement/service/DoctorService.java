
  package com.hm.HospitalManagement.service;
  
  import com.hm.HospitalManagement.entity.Appointment;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.entity.Patient;
import com.hm.HospitalManagement.repository.AppointmentRepository;
import com.hm.HospitalManagement.repository.DoctorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.stereotype.Service;
  
  import java.util.List; 
  import java.util.Optional;
import java.util.stream.Collectors;
  
  @Service 
  public class DoctorService {
  
  @Autowired 
  private DoctorRepository doctorRepository;
  @Autowired
  private AppointmentRepository appointmentRepo;
  
  private static final Logger log = LoggerFactory.getLogger(DoctorService.class);
  
  // Get all doctors
  public List<Doctor> getAllDoctors() { 
	  log.info("getting all doctors");   		// Log for Doctor Fetch Message
	  return doctorRepository.findAll(); }
  
  // Add a new doctor 
  public void addDoctor(Doctor doctor) {
  doctorRepository.save(doctor); }
  
  
  public Optional<Doctor> getDoctorById(Long id)
  {
	  return doctorRepository.findById(id);
  }
  
  
  public List<Patient> getPatientsByDoctorId(Long doctorId) {
      List<Appointment> appointments = appointmentRepo.findByDoctorId(doctorId);
      
      return appointments.stream()
              .map(Appointment::getPatient)
              .distinct()
              .collect(Collectors.toList());
  }
  
  

  
  public void updateDoctor(Doctor doctor)
  {
	  if(doctorRepository.existsById(doctor.getId()))
	  {
		  doctorRepository.save(doctor);
	  }
  }
  
  // Delete a doctor 
  public void deleteDoctor(Long id) {
  doctorRepository.deleteById(id); } 
  
  //Added
  public Doctor getDoctorByEmail(String email)
  {
	  return doctorRepository.findByEmail(email);
  }
  
  }

  
