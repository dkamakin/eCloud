package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }

}
