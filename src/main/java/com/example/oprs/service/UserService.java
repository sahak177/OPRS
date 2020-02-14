package com.example.oprs.service;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.Officer;
import com.example.oprs.pojo.User;

public interface UserService {

    User getUserByEmail(String username);

    boolean add(User user);

    boolean addAsAdmin(Officer officer);

    boolean updatePassword(String encodedNewPassword, String currentUser);

    void validateInput(User user) throws InValidInputException;

    boolean addToken(Long userId, String token);
}
