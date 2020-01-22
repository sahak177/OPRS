package com.example.oprs.service;

import com.example.oprs.model.User;

public interface UserService {
    User getUserByEmail(String username);

    boolean init(User user);
}
