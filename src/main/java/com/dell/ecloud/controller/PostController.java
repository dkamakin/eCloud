package com.dell.ecloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post")
    public String showMainPage() {
        return "post";
    }

}
