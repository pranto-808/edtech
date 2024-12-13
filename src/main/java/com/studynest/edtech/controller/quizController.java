package com.studynest.edtech.controller;

import com.studynest.edtech.model.quizModel;
import com.studynest.edtech.model.questionModel;
import com.studynest.edtech.model.stuanswerModel;
import com.studynest.edtech.repository.quizRepository;
import com.studynest.edtech.repository.questionRepository;
import com.studynest.edtech.repository.stuanswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/quiz")
public class quizController {

    private static final Logger logger = LoggerFactory.getLogger(quizController.class);

    @Autowired
    private quizRepository quizRepository;

    @Autowired
    private questionRepository questionRepository;

    @Autowired
    private stuanswerRepository stuanswerRepository;

    @GetMapping
    public String showQuizAccessPage() {
        return "quiz-access"; // Ensure this template exists
    }

    @PostMapping("/validate-passcode")
public String validatePasscode(@RequestParam("passcode") String passcode, Model model) {
    String defaultPasscode = "12345";

    try {
        quizModel quiz = quizRepository.findByPasscode(passcode);

        if (defaultPasscode.equals(passcode) || (quiz != null && quiz.getQuestions() != null)) {
            model.addAttribute("questions", quiz != null ? quiz.getQuestions() : null);
            return "quiz-questions"; // Ensure this template exists
        } else {
            model.addAttribute("error", "Invalid passcode. Please try again.");
            return "quiz-access";
        }
    } catch (Exception e) {
        logger.error("Error retrieving quiz:", e);
        model.addAttribute("error", "An error occurred. Please try again later.");
        return "quiz-access";
    }
}

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> answers, Model model) {
        // Retrieve the quiz based on passcode (optional, if tracking by quiz)
        String passcode = answers.get("quizPasscode");
        quizModel quiz = quizRepository.findByPasscode(passcode); // Retrieve the quiz based on passcode

        // Evaluate answers and calculate marks
        int score = 0;
        int totalMarks = 0;

        for (String questionId : answers.keySet()) {
            if ("quizPasscode".equals(questionId)) {
                continue; // Skip the passcode field from the answers map
            }

            questionModel question = questionRepository.findById(Long.parseLong(questionId)).orElse(null);
            if (question != null) {
                String studentAnswer = answers.get(questionId);
                boolean isCorrect = studentAnswer.equalsIgnoreCase(question.getCorrectAnswer());

                score += isCorrect ? 1 : 0;
                totalMarks++;

                // Save student's answer with quiz reference
                stuanswerModel studentAnswerEntity = new stuanswerModel();
                studentAnswerEntity.setQuestionText(question.getQuestion());
                studentAnswerEntity.setCorrectAnswer(question.getCorrectAnswer());
                studentAnswerEntity.setStudentAnswer(studentAnswer);
                studentAnswerEntity.setCorrect(isCorrect);
                studentAnswerEntity.setMarks(isCorrect ? 1 : 0);
                studentAnswerEntity.setQuiz(quiz);  // Link answer to quiz

                stuanswerRepository.save(studentAnswerEntity);
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("totalMarks", totalMarks);
        return "quiz-results";
    }
}
