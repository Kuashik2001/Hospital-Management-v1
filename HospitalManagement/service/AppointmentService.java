package com.hm.HospitalManagement.service;

import com.hm.HospitalManagement.entity.Appointment;
import com.hm.HospitalManagement.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void bookAppointment(Appointment appointment) {
        logger.info("Booking appointment with ID: {}", appointment.getId());
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        logger.info("Fetching appointments for doctor with ID: {}", doctorId);
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public void updateAppointmentStatus(Long id, String status) {
        logger.info("Updating status of appointment with ID: {} to {}", id, status);
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointment.get().setStatus(status);
            appointmentRepository.save(appointment.get());
        } else {
            logger.warn("Appointment with ID: {} not found", id);
        }
    }

    public void deleteAppointment(Long id) {
        logger.info("Deleting appointment with ID: {}", id);
        appointmentRepository.deleteById(id);
    }
}
