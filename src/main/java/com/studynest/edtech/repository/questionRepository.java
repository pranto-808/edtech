package com.studynest.edtech.repository;

import com.studynest.edtech.model.questionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface questionRepository extends JpaRepository<questionModel, Long> {
}
