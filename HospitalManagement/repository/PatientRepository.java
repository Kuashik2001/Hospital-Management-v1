package com.hm.HospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.HospitalManagement.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);  // Find Patient by Email for Login
}
