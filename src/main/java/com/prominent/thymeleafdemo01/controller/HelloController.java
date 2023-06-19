package com.prominent.thymeleafdemo01.controller;

 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;

 import java.util.Date;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String showHome() {

        return "home";
    }

    // add request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    // add request mapping for /systems

    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }
}
