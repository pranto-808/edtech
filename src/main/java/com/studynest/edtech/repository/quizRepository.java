package com.studynest.edtech.repository;

import com.studynest.edtech.model.quizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizRepository extends JpaRepository<quizModel, Long> {
    quizModel findByPasscode(String passcode);
}
