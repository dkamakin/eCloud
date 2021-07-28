package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NavbarController {

    @GetMapping("/navbar")
    public ModelAndView showNavbar() {
        return new ModelAndView("navbar");
    }

}
