package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavbarController {

    @GetMapping("/navbar")
    public String showNavbar() {
        return "navbar";
    }

}
