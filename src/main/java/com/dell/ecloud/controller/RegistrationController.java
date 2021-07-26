package com.dell.ecloud.controller;

import com.dell.ecloud.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(String nickname, String username, String password) {
        log.info("Registration of a new user: {}", username);
        userService.saveUser(nickname, username, password);
        return "redirect:/login";
    }

}
