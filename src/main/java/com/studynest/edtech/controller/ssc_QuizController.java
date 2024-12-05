package com.studynest.edtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ssc_QuizController {

    @GetMapping("/ssc_quiz")
    public String showQuizPage() {
        return "ssc_quiz"; // Name of the HTML file (quiz.html)
    }
}
