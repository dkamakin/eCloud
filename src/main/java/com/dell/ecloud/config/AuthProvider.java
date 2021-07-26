package com.dell.ecloud.config;

import com.dell.ecloud.model.User;
import com.dell.ecloud.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.info("Authenticate user: {}", username);

        User user = (User) userService.loadUserByUsername(username);

        if (user == null) {
            log.warn("Trying find the user by nickname");
            user = (User) userService.loadUserByNickname(username);
        }

        if (user != null && (user.getUsername().equals(username) || user.getNickname().equals(username))) {
            if (!password.matches(user.getPassword())) {
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
