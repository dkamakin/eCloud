package com.dell.ecloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserProfileController {

    @Autowired
    public UserDetailsService userDetailsService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public String showRegistrationPage() {
        return "profile";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile/info")
    @ResponseBody
    public ResponseEntity<UserDetails> getUserInfo() {
        String nickname = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        log.info("Getting user info {}", nickname);
        return ResponseEntity.ok(userDetailsService.loadUserByUsername(nickname));
    }

}
