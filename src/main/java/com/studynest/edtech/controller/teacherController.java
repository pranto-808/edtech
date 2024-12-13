package com.studynest.edtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studynest.edtech.repository.quizRepository;
import com.studynest.edtech.repository.teacherRepository;
import com.studynest.edtech.model.quizModel;
import com.studynest.edtech.model.teacherModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class teacherController {

    @Autowired
    private teacherRepository teacherRepository;

    @Autowired
    private quizRepository quizRepository;

    @GetMapping("/become-a-teacher")
    public String showRegistrationForm() {
        return "become-a-teacher"; // Refers to `templates/become-a-teacher.html`
    }

    @PostMapping("/become-a-teacher")
    public String registerTeacher(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("expertise") String expertise,
            @RequestParam("resume") MultipartFile resume,
            Model model) {

        try {
            // Save teacher info to the database
            teacherModel teacher = new teacherModel(fullName, email, phone, expertise);
            teacherRepository.save(teacher);

            // Save resume file (if uploaded)
            if (!resume.isEmpty()) {
                String uploadDir = "uploads/resumes/";
                Path path = Paths.get(uploadDir + resume.getOriginalFilename());
                Files.createDirectories(path.getParent());
                Files.copy(resume.getInputStream(), path);
            }

            return "redirect:/teacher-success"; // Redirect to success page
        } catch (Exception e) {
            model.addAttribute("error", "Failed to register. Please try again.");
            return "become-a-teacher"; // Stay on the same page if registration fails
        }
    }

    @GetMapping("/teacher-success")
    public String showSuccessPage() {
        return "teacher-success"; // Refers to `templates/teacher-success.html`
    }

    @GetMapping("/teacher/dashboard")
    public String showTeacherDashboard(Model model) {
        // Retrieve all quizzes
        List<quizModel> quizzes = quizRepository.findAll();
        model.addAttribute("quizzes", quizzes);

        return "teacher-dashboard"; // Refers to `templates/teacher-dashboard.html`
    }

    @GetMapping("/quiz/delete/{id}")
    public String deleteQuiz(@PathVariable("id") Long id, Model model) {
        try {
            quizRepository.deleteById(id);
            return "redirect:/teacher/dashboard"; // Redirect back to the dashboard after deletion
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete quiz. Please try again.");
            return "teacher-dashboard"; // Reload the page with the error
        }
    }
}
