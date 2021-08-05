package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/navbar")
public class NavbarController {

    @GetMapping
    public ModelAndView showNavbar() {
        return new ModelAndView("navbar");
    }

}
