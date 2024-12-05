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

@Controller
@RequestMapping("/quiz")
public class quizController {

    @Autowired
    private quizRepository quizRepository;

    @Autowired
    private questionRepository questionRepository;

    @Autowired
    private stuanswerRepository stuanswerRepository;

    @PostMapping("/validate-passcode")
    public String validatePasscode(@RequestParam("passcode") String passcode, Model model) {
        quizModel quiz = quizRepository.findByPasscode(passcode);
        if (quiz != null) {
            model.addAttribute("questions", quiz.getQuestions());
            return "quiz-questions";
        } else {
            model.addAttribute("error", "Invalid passcode. Try again.");
            return "quiz-access";
        }
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> answers, Model model) {
        // Evaluate answers and calculate marks
        int score = 0;
        int totalMarks = 0;

        for (String questionId : answers.keySet()) {
            questionModel question = questionRepository.findById(Long.parseLong(questionId)).orElse(null);
            if (question != null) {
                String studentAnswer = answers.get(questionId);
                boolean isCorrect = studentAnswer.equalsIgnoreCase(question.getCorrectAnswer());

                score += isCorrect ? 1 : 0;
                totalMarks++;

                // Save student's answer
                stuanswerModel studentAnswerEntity = new stuanswerModel();
                studentAnswerEntity.setQuestionText(question.getQuestion());
                studentAnswerEntity.setCorrectAnswer(question.getCorrectAnswer());
                studentAnswerEntity.setStudentAnswer(studentAnswer);
                studentAnswerEntity.setCorrect(isCorrect);
                studentAnswerEntity.setMarks(isCorrect ? 1 : 0);

                stuanswerRepository.save(studentAnswerEntity);
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("totalMarks", totalMarks);
        return "quiz-results";
    }
}
