package com.example.oprs.repository;

import com.example.oprs.dao.User;


public interface UserRepository {
    User getUserByEmail(String username);

    boolean save(User user);

    boolean saveFromAdmin(User officer);

    boolean updatePassword(String encodedNewPassword, String currentUser);

    boolean addToken(Long userId, String token);
}
