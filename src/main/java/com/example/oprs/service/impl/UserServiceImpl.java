package com.example.oprs.service.impl;

import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import com.example.oprs.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //if i use been injection receive circular dependency ???
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public void init(User user) {
        String encodedPassword =passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
