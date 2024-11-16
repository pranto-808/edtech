package com.studynest.edtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class coursesController {
    @GetMapping("/courses")
    public String courses() {
        return "courses"; // Renders the courses.html page
    }
   

    @GetMapping("/courses/allcourses")
    public String allCourses() {
        return "courses"; // Renders the same courses.html page, but you could add logic if needed
    }

    @GetMapping("/courses/skills")
    public String skillsCourses() {
        return "courses"; // Renders the same courses.html page for filtering Skills courses
    }

    @GetMapping("/courses/admission")
    public String admissionCourses() {
        return "courses"; // Renders the same courses.html page for filtering Admission courses
    }

    @GetMapping("/courses/hsc")
    public String hscCourses() {
        return "courses"; // Renders the same courses.html page for filtering HSC courses
    }

    @GetMapping("/courses/ssc")
    public String sscCourses() {
        return "courses"; // Renders the same courses.html page for filtering SSC courses
    }
    
}