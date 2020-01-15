package com.example.oprs.service.impl;

import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public void init(User user) {
        userRepository.save(user);
    }
}
