package com.example.oprs.service.impl;

import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import com.example.oprs.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public boolean add(User user) {
        String encodedPassword =passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}
