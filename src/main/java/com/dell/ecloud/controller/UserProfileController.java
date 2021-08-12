package com.dell.ecloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserProfileController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("profile");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<UserDetails> getUserInfo() {
        String nickname = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        log.info("Getting user info {}", nickname);
        return ResponseEntity.ok(userDetailsService.loadUserByUsername(nickname));
    }

}
