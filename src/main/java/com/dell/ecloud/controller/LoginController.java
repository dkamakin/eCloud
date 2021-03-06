package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView authorize() {
        return new ModelAndView("redirect:/");
    }

}
