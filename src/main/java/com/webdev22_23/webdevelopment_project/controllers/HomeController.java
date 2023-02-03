package com.webdev22_23.webdevelopment_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}