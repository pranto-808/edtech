package com.studynest.edtech.controller;

import com.studynest.edtech.model.quizModel;
import com.studynest.edtech.model.teacherModel;
import com.studynest.edtech.repository.quizRepository;
import com.studynest.edtech.repository.teacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class teacher_QuizController {

    @Autowired
    private quizRepository quizRepository;

    @Autowired
    private teacherRepository teacherRepository;

    // GET: Show the Add Quiz form
    @GetMapping("/add-quiz")
    public String showQuizCreationForm(Model model) {
        model.addAttribute("quiz", new quizModel());
        return "add-quiz"; // Refers to templates/add-quiz.html
    }

    // POST: Create a new quiz
    @PostMapping("/add-quiz")
    public String createQuiz(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("passcode") String passcode,
            Model model) {
        quizModel quiz = new quizModel();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setPasscode(passcode);

        // Assuming a default teacher ID for now, update with dynamic logic if needed
        Optional<teacherModel> defaultTeacher = teacherRepository.findById(1L);
        if (defaultTeacher.isPresent()) {
            quiz.setTeacher(defaultTeacher.get());
            quizRepository.save(quiz);
            return "redirect:/success";
        } else {
            model.addAttribute("error", "No default teacher found. Please ensure a teacher exists.");
            return "add-quiz";
        }
    }

    // GET: Quiz creation success page
    @GetMapping("/quiz-success")
    public String showQuizSuccess() {
        return "quiz-success"; // Refers to templates/quiz-success.html
    }
}
