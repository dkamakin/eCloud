package com.dell.ecloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping("/navbar")
    public String showLoginPage() {
        return "navbar";
    }
}
