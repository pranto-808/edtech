package com.studynest.edtech.controller;

import com.studynest.edtech.model.courseModel;
import com.studynest.edtech.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailedCourseController {

    @Autowired
    private courseService courseService; // Inject CourseService

    @GetMapping("/course-details")
    public String courseDetails(@RequestParam("courseId") Long courseId, Model model) {
        // Retrieve course details using courseId and pass it to the model
        courseModel course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "course-details"; // Render course-details.html
    }
}
