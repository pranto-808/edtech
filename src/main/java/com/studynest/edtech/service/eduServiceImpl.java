package com.studynest.edtech.service;

import com.studynest.edtech.model.eduModel;
import com.studynest.edtech.repository.eduRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class eduServiceImpl implements eduService {

    // Dependency on eduRepository to perform database operations
    private final eduRepository eduRepository;

    // Constructor-based injection of eduRepository
    //@Autowired
    public eduServiceImpl(eduRepository eduRepository) {
        this.eduRepository = eduRepository;
    }

    // Implementation of findByUsername method to retrieve a user by username
    @Override
    public Optional<eduModel> findByUsername(String username) {
        return eduRepository.findByUsername(username);
    }

    // Implementation of validateUser method to validate username and password
    @Override
    public boolean validateUser(String username, String password) {
        Optional<eduModel> user = eduRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
    // Implementation of saveUser method to save or update a user
    @Override
    public void saveUser(eduModel user) {
        eduRepository.save(user);
    }
}
