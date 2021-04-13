package com.dell.ecloud.controller;

import com.dell.ecloud.model.Role;
import com.dell.ecloud.model.User;
import com.dell.ecloud.model.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

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
        log.info("Registration of a new user: " + username);
        User user = new User(username, password, nickname, null, 0L);
        user.setRoles(Collections.singleton(Role.USER));
        userService.saveUser(user);
        return "redirect:/login";
    }

}
