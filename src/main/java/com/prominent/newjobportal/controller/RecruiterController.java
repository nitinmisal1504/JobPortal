package com.prominent.newjobportal.controller;

import com.prominent.newjobportal.model.Recruiter;
import com.prominent.newjobportal.service.RecruiterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/recruiters")
public class RecruiterController {
    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @RequestMapping("/")
    public String displayHomePage(){
        return "home";
    }
    @RequestMapping("/RecruitersPage")
    public String displayRecruitersPage(){
        return "recruiter_home";
    }

    @RequestMapping("/viewRecruitersPage")
    public String viewRecruitersPage(Model model){
        model.addAttribute("listRecruiters",recruiterService.getAllRecruiters());
        return "viewRecruiters";
    }


    @RequestMapping(value = "/showFormForAdd")
    private String showAddRecruiterForm(Model model) {
        Recruiter recruiter=new Recruiter();
        model.addAttribute("recruiter",recruiter);
        return "addRecruiter";
    }

    @PostMapping(value = "/saveRecruiter")
    private String saveRecruiter(@ModelAttribute("student") Recruiter recruiter) {
        recruiterService.saveRecruiter(recruiter);
        return "redirect:/LogInRecruiterController/logInPage";
    }

    @GetMapping(value = "/showFormForUpdate/{id}")
    private String updateRecruiter(@PathVariable(value = "id")int id, Model model) {
        Recruiter recruiter=recruiterService.getRecordById(id);
        model.addAttribute("recruiter",recruiter);
        return "updateRecruiter";
    }


    @GetMapping(value = "/deleteRecruiter/{id}")
    private String deleteRecruiter(@PathVariable (value = "id") int id) {
        recruiterService.deleteRecordById(id);
        return "redirect:/viewRecruiters";
    }
}

