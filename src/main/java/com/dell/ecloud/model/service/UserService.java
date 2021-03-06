package com.dell.ecloud.model.service;

import com.dell.ecloud.model.Role;
import com.dell.ecloud.model.entity.User;
import com.dell.ecloud.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByNickname(String nickname) throws UsernameNotFoundException {
        log.info("UserService: searching for user: " + nickname);
        User user = userRepository.findByNickname(nickname);

        if (user == null)
            throw new UsernameNotFoundException("Error: user not found.");

        log.info("Found user " + user.getUsername());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email);

        if (user == null)
            throw new UsernameNotFoundException("Error: user not found.");

        log.info("Found user " + user.getUsername());
        return user;
    }

    public boolean saveUser(User user) {
        log.info("Saving a new user: " + user.getUsername() + " | " + user.getNickname());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            log.info("User is already registered");
            return false;
        }

        userRepository.save(user);
        log.info("User successfully registered");
        return true;
    }

    public boolean saveUser(String nickname, String username, String password) {
        User user = new User(username, passwordEncoder.encode(password),
                nickname, null, 0L);
        user.setRole(Role.ROLE_USER);
        return saveUser(user);
    }

}
