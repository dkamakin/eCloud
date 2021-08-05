package com.dell.ecloud.controller;

import com.dell.ecloud.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView showRegistration() {
        return new ModelAndView("registration");
    }

    @PostMapping
    public ModelAndView saveUser(String nickname, String username, String password) {
        log.info("Registration of a new user: {}", username);
        userService.saveUser(nickname, username, password);
        return new ModelAndView("redirect:/login");
    }

}
