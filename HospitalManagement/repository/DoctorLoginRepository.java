package com.hm.HospitalManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.HospitalManagement.entity.Doctor;

@Repository
public interface DoctorLoginRepository extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String email);
}
