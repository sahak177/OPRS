package com.example.oprs.repository;

import com.example.oprs.pojo.Officer;
import com.example.oprs.pojo.User;


public interface UserRepository {
    User getUserByEmail(String username);

    boolean save(User user);

    boolean saveFromAdmin(Officer officer);

    boolean updatePassword(String encodedNewPassword, String currentUser);

    boolean addToken(Long userId, String token);
}
