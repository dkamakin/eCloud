package com.dell.ecloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooterController {

    @GetMapping("/footer")
    public String showFooter() {
        return "footer";
    }

}
