package com.dell.ecloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post")
    public String showMainPage() {
        return "post";
    }

}
