package com.studynest.edtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class quizModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passcode;

    @OneToMany(mappedBy = "quiz")
    private List<questionModel> questions;  // A quiz can have many questions

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public List<questionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<questionModel> questions) {
        this.questions = questions;
    }


    public void setDescription(String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDescription'");
    }

    public void setTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTitle'");
    }

    public void setTeacher(teacherModel teacherModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTeacher'");
    }
}
