package com.dell.ecloud.config;

import com.dell.ecloud.model.entity.User;
import com.dell.ecloud.model.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
@Getter
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.info("Authenticate user: {} : {}", username, password);

        User user = (User) userService.loadUserByUsername(username);

        if (user == null) {
            log.warn("Trying find the user by nickname");
            user = (User) userService.loadUserByNickname(username);
        }

        if (user != null && (user.getUsername().equals(username) || user.getNickname().equals(username))) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                log.warn("Wrong password");
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            log.info("User successfully authenticated, role = {}", user.getRole());
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        } else {
            log.warn("User not found");
            throw new BadCredentialsException("Username not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
