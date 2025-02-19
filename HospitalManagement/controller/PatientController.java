
package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.entity.Patient;
import com.hm.HospitalManagement.service.DoctorService;
import com.hm.HospitalManagement.service.PatientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

@Controller

@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/signup")
	public String showSignupPage(Model model) {
		return "patient-signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute Patient patient, Model model) {
		Patient existingPatient = patientService.validatePatient(patient.getEmail(),patient.getPassword());
		if (existingPatient != null) {
			model.addAttribute("error", "Email already exists!");
			return "patient-signup";
		}

		//Patient patient = new Patient();
		
		patientService.registerPatient(patient);
		return "redirect:/patient/login";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "patient-login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email,

			@RequestParam String password, HttpSession session, Model model) {
		Patient patient = patientService.validatePatient(email, password);

		if (patient != null) {
			session.setAttribute("patientName", patient.getName());
			session.setAttribute("pid", patient.getId());
			return "redirect:/patient/dashboard";
		} else {
			model.addAttribute("error", "Wrong email or password");
			return "patient-login";
		}
	}

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model, HttpServletResponse response) {
		String patientName = (String) session.getAttribute("patientName");

		if (patientName == null) {
			return "redirect:/patient/login";
		}

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		// Adding
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctors", doctors);

		model.addAttribute("patientMessage","Hi "+ patientName);
		return "patient-dashboard";
	}

	@GetMapping("/patient-dashboard")
	public String showPatientDashboard(HttpSession session, Model model, HttpServletResponse response) {
		String patientName = (String) session.getAttribute("patientName");

		if (patientName == null) {
			return "redirect:/patient/login";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctors", doctors);
		return "patient-dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		return "redirect:/";
	}

	// New Added
	@PostMapping("/add")
	public String addPatient(@ModelAttribute Patient patient, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		patientService.registerPatient(patient);
		return "redirect:/patient/dashboard";
	}

	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		patientService.updatePatient(patient);
		return "redirect:/patient/dashboard";
	}

	@GetMapping("/delete/{id}")
	public String deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return "redirect:/patient/dashboard";
	}
}
