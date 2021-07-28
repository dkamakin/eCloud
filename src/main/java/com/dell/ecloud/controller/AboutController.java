package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AboutController {

    @GetMapping("/about")
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }

}
