/*package com.hm.HospitalManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.HospitalManagement.entity.Admin;
import com.hm.HospitalManagement.repository.AdminRepository;

@Service
public class AdminService {
	private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminRepository adminRepository;

    public Admin validateAdmin(String email, String password) {
    	log.info("Admin Method:{}",email,password);
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin; // Return the admin object
        }
        return null;
    }
}*/

package com.hm.HospitalManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.HospitalManagement.entity.Admin;
import com.hm.HospitalManagement.repository.AdminRepository;

@Service
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    public Admin validateAdmin(String email, String password) {
        log.info("Validating admin with email: {}", email);
        try {
            Admin admin = adminRepository.findByEmail(email);
            if (admin != null && admin.getPassword().equals(password)) {
                log.info("Admin validation successful for email: {}", email);
                return admin; // Return the admin object
            } else {
                log.warn("Admin validation failed for email: {}", email);
            }
        } catch (Exception e) {
            log.error("Error occurred while validating admin with email: {}", email, e);
        }
        return null;
    }
}
