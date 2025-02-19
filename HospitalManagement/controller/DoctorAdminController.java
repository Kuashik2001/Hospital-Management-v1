package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.service.DoctorService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorAdminController {

    @Autowired
    private DoctorService doctorService;
    


    @GetMapping("/list")
    public String listDoctors(Model model,HttpSession session) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        
        //Adding
        String adminName = (String) session.getAttribute("adminName");
        model.addAttribute("doctors", doctors);
        model.addAttribute("adminMessage", "Hi " + adminName);
        //clear caching
        
        return "admin-dashboard";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
    	
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
    	
        doctorService.updateDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
    	
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/list";
    }
    
    
    
    

}
