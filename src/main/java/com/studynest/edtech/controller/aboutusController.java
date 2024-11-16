package com.studynest.edtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutusController {
    
        @GetMapping("/aboutus")
        public String aboutus() {
            return "aboutus"; // Renders the aboutus.html page
        }
    
}
