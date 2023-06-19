package com.prominent.newjobportal.controller;

import com.prominent.newjobportal.model.Recruiter;
import com.prominent.newjobportal.service.RecruiterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/LogInRecruiterController")
public class LogInRecruiterController {
    @RequestMapping("/logInPage")
    public String displayHomePage(){

        return "LogInRecruiter";
    }
    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }
}

