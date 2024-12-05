package com.studynest.edtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ssc_enrollmentController {

    @GetMapping("/ssc-course-details")
public String showCourseDetails(@RequestParam(name = "courseId", required = false) Long courseId, Model model) {
    // Logic for SSC course details
    model.addAttribute("courseId", courseId);
    model.addAttribute("courseName", "SSC '25 Science");
    model.addAttribute("price", 5000);
    return "ssc_enrolled_dashboard";
}
    // Handle the enrollment process
    @PostMapping("/enroll")
    public String handleEnrollment(@RequestParam(name = "courseId") Long courseId, Model model) {
        // Add enrollment logic here (e.g., save the user enrollment to the database)
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", "SSC '25 Science");
        return "redirect:/dashboard";
    }

    // Display the dashboard page after enrollment
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Add the logic to fetch user-specific details (e.g., course progress, materials)
        model.addAttribute("courseName", "SSC '25 Science");
        model.addAttribute("progress", 70); // Example progress percentage
        return "dashboard";
    }
}
