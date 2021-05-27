package com.dell.ecloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

    @GetMapping("/footer")
    public String showFooter() {
        return "footer";
    }

}
