package com.hm.HospitalManagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.HospitalManagement.entity.Patient;
import com.hm.HospitalManagement.repository.PatientRepository;

@Service
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    
    @Autowired
    private PatientRepository patientRepository;

    // Register a new patient
    public void registerPatient(Patient patient) {
        log.info("Registering new patient with email: {}", patient.getEmail());
        patientRepository.save(patient);
    }

    // Validate Patient Login
    public Patient validatePatient(String email, String password) {
        log.info("Validating login for patient with email: {}", email);
        Patient patient = patientRepository.findByEmail(email);
        if (patient != null && patient.getPassword().equals(password)) {
            log.info("Login successful for patient with email: {}", email);
            return patient;  // Login Successful
        }
        log.warn("Login failed for patient with email: {}", email);
        return null;  // Login Failed
    }

    // Get patient by ID
    public Optional<Patient> getPatientById(Long id) {
        log.info("Fetching patient with ID: {}", id);
        return patientRepository.findById(id);
    }

    // Update patient details
    public void updatePatient(Patient patient) {
        log.info("Updating patient with ID: {}", patient.getId());
        if (patientRepository.existsById(patient.getId())) {
            patientRepository.save(patient);
        } else {
            log.warn("Patient with ID: {} not found", patient.getId());
        }
    }

    // Delete a patient
    public void deletePatient(Long id) {
        log.info("Deleting patient with ID: {}", id);
        patientRepository.deleteById(id);
    }

    // Get patient by email
    public Patient getPatientByEmail(String email) {
        log.info("Fetching patient with email: {}", email);
        return patientRepository.findByEmail(email);
    }
}
