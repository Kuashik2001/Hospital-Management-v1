package com.hm.HospitalManagement.service;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.repository.DoctorLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorLoginService {

	 @Autowired
	    private DoctorLoginRepository doctorLoginRepository;

	    public Doctor findByEmail(String email) {
	        return doctorLoginRepository.findByEmail(email);
	    }
}




