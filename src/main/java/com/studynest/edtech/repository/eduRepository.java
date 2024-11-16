package com.studynest.edtech.repository;

import com.studynest.edtech.model.eduModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface eduRepository extends JpaRepository<eduModel, Long> {
    Optional<eduModel> findByUsername(String username); // Ensure this method exists
}
