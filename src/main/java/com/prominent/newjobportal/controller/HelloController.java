package com.prominent.newjobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller

public class HelloController {
    @RequestMapping("/")
    public String sayHello(){
         return "index";

    }
}
