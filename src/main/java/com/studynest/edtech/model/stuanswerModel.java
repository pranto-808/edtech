package com.studynest.edtech.model;

import jakarta.persistence.*;

@Entity
public class stuanswerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private quizModel quiz;

    private String questionText;
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    private String studentAnswer;
    public String getStudentAnswer() {
        return studentAnswer;
    }
    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
    private String correctAnswer;
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    private boolean isCorrect;
    public boolean isCorrect() {
        return isCorrect;
    }
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    private int marks;
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }

     

    // Getters and Setters
    // Add necessary getters and setters
}
