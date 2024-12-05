package com.studynest.edtech.repository;

import com.studynest.edtech.model.teacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface teacherRepository extends JpaRepository<teacherModel, Long> {
}
