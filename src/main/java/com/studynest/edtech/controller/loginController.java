package com.studynest.edtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studynest.edtech.model.eduModel;
import com.studynest.edtech.service.eduService;

@Controller
public class loginController {

    @Autowired
    private eduService eduService;

    @GetMapping("/login")
    public String showLoginPage() {
        System.out.println("Login page accessed"); // Debugging log
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        if (eduService.validateUser(username, password)) {
            System.out.println("Login successful for user: " + username);
            return "redirect:/home"; // Redirect to the home page if validation is successful
        } else {
            System.out.println("Login failed for user: " + username);
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Stay on the login page if validation fails
        }
    }

    // Show sign-up page (GET method)
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup"; // This should map to a `signup.html` template
    }

    @PostMapping("/signup")
    public String register(@RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("number") String number,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        eduModel user = new eduModel();
        user.setName(name);
        user.setUsername(username);
        user.setNumber(number);
        user.setEmail(email);
        user.setPassword(password);

        eduService.saveUser(user);

        model.addAttribute("success", "Registration successful! Please log in.");
        return "redirect:/login";
    }

}
