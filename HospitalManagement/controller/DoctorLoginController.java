package com.hm.HospitalManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hm.HospitalManagement.entity.Appointment;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.service.AppointmentService;
import com.hm.HospitalManagement.service.DoctorLoginService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/doctor")
public class DoctorLoginController {

	@Autowired
	private DoctorLoginService doctorLoginService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/login")
	public String showLoginPage() {
		return "doctor-login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		Doctor doctor = doctorLoginService.findByEmail(email);

		if (doctor != null && doctor.getDateOfJoining().toString().equals(password)) {
			session.setAttribute("doctorName", doctor.getName());
			session.setAttribute("doctor", doctor);
			return "redirect:/doctor/dashboard";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "doctor-login";
		}
	}

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model, HttpServletResponse response) {
		String doctorName = (String) session.getAttribute("doctorName");
		String doctorEmail = (String) session.getAttribute("email");
		if (doctorName == null) {
			return "redirect:/doctor/login";
		}

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		List<Appointment> appointments = null;
		if (session != null) {
			Doctor doctor = (Doctor) session.getAttribute("doctor");
			System.out.println(doctor);
			appointments = this.appointmentService.getAppointmentsByDoctor(doctor.getId());
			System.out.println(appointments);
		}
		model.addAttribute("doctorMessage", "Welcome Dr. " + doctorName);
		model.addAttribute("appointments", appointments);
		// session.setAttribute("appointments", appointments);
		return "doctor-dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		return "redirect:/";
	}
}
