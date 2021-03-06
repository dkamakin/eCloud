package com.dell.ecloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/post")
public class PostController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ModelAndView showPost() {
        return new ModelAndView("post");
    }

}
