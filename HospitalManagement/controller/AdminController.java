package com.hm.HospitalManagement.controller;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hm.HospitalManagement.entity.Admin;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.service.AdminService;
import com.hm.HospitalManagement.service.DoctorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private DoctorService doctorService;
	
	
	
	@GetMapping("/")
	public String showHome()
	{
		
		return "index";
	}
	@GetMapping("/patientLogin")
	public String patientLogin( ) {
		
		return "patient-login";
	}
	@GetMapping("/patientSignup")
	public String patientSignup() {
	
		return "patient-signup";
	}
	
	@GetMapping("/doctorLogin")
	public String doctorLogin( ) {
	
		return "doctor-login";
	}
	@GetMapping("/adminLogin")
	public String showAdminLoginPage() {
		
		return "admin-login";
	}
	
	

    @GetMapping("/dashboard")
    public String showLoginPage(HttpSession session, Model model, HttpServletResponse response) {
       String adminName = (String) session.getAttribute("adminName");

        if (adminName == null) {
//           model.addAttribute("adminMessage", "Welcome Admin, " + adminName);
        	return "redirect:/admin/adminLogin";
        }
        
         
        List<Doctor> doctors =doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        
        //Disable Caching
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires","0");
        model.addAttribute("adminMessage", "Hi " + adminName);
        return "admin-dashboard";
    }

    @PostMapping("/adminLogin")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
    
        Admin admin = adminService.validateAdmin(email, password);

        if (admin != null) {
            session.setAttribute("adminName", admin.getAdminName()); // Store admin name in session
            return "redirect:/admin/dashboard"; // Redirect to update welcome message
        } else {
            model.addAttribute("error", "Wrong email or password");
            return "admin-login";
        }
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
