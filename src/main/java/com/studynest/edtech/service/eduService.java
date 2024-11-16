package com.studynest.edtech.service;

import com.studynest.edtech.model.eduModel;
import java.util.Optional;

public interface eduService {

    // Method to save a new user or update an existing user in the database
  default void saveUser(eduModel user) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
      }
   
        //void saveUser(eduModel user);
    
    // Method to find a user by their username
    Optional<eduModel> findByUsername(String username);

    // Method to validate if the provided username and password match a user in the database
    boolean validateUser(String username, String password);
}
