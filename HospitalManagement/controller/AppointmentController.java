package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.entity.Appointment;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.entity.Patient;
import com.hm.HospitalManagement.service.AppointmentService;
import com.hm.HospitalManagement.service.DoctorService;
import com.hm.HospitalManagement.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/bookForm")
    public String showAppointmentForm(@RequestParam Long doctorId, HttpSession session, Model model) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(doctorId);
        if (doctorOptional.isPresent()) {
            model.addAttribute("doctor", doctorOptional.get());
            return "appointment-form";
        }
        return "redirect:/patient/dashboard";
    }
    

    @PostMapping("/book")
    public String bookAppointment(@ModelAttribute Appointment appointment,HttpSession httpSession,@RequestParam("doctorId") String doctorId) {
    	//System.out.println("inside the bookAppointment"+doctorId+","+disease+","+age+","+gender+","+phoneNumber+","+appointmentDate);
    	System.out.println(appointment);
    	Doctor doctorObject=doctorService.getDoctorById(Long.parseLong(doctorId)).get();
    
    	Long pid=(Long)(httpSession.getAttribute("pid"));
    	
    	Patient patientObject=this.patientService.getPatientById(pid).get();
    	
    	appointment.setPatient(patientObject);
    	appointment.setDoctor(doctorObject);
    
    	System.out.println(appointment);
    	
    	this.appointmentService.bookAppointment(appointment);
    	System.out.println("After storing...");
    	
    	
        return "redirect:/patient/dashboard";
    }
    
    @GetMapping("/doctorAppointments")
    public String showDoctorAppointments(HttpSession session, Model model) {
        Long doctorId = (Long) session.getAttribute("doctorId");

        if (doctorId == null) {
            return "redirect:/doctor/login";
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor-dashboard";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate(); // Clear session

        //Disable Caching
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires","0");
        
        return "redirect:/";
    }

    }
