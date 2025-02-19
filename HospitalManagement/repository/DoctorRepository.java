/*
 * package com.hm.HospitalManagement.Repository;
 * 
 * import com.hm.HospitalManagement.Entity.Doctor; import
 * org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.stereotype.Repository;
 * 
 * @Repository public interface DoctorRepository extends JpaRepository<Doctor,
 * Long> { }
 */

package com.hm.HospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.HospitalManagement.entity.Doctor;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String email);
}
