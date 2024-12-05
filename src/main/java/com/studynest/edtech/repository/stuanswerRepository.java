package com.studynest.edtech.repository;

import com.studynest.edtech.model.stuanswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface stuanswerRepository extends JpaRepository<stuanswerModel, Long> {
}
