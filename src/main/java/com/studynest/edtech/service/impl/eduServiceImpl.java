package com.studynest.edtech.service.impl;

import com.studynest.edtech.model.eduModel;
import com.studynest.edtech.repository.eduRepository;
import com.studynest.edtech.service.eduService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class eduServiceImpl implements eduService {

    @Autowired
    private eduRepository eduRepository;

    @Override
    public void saveUser(eduModel user) {
        eduRepository.save(user); // Save user to the database
    }

    @Override
    public Optional<eduModel> findByUsername(String username) {
        return eduRepository.findByUsername(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<eduModel> user = eduRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
