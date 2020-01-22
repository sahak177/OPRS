package com.example.oprs.repository;

import com.example.oprs.model.User;


public interface UserRepository {
    User getUserByEmail(String username);

    boolean save(User user);
}
