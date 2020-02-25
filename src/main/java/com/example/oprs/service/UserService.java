package com.example.oprs.service;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.User;

import java.io.IOException;

public interface UserService {

    User getUserByEmail(String username);

    boolean add(User user);

    boolean addAsAdmin(User officer,String officerRole);

    boolean updatePassword(String encodedNewPassword, String currentUser);

    boolean addToken(Long userId, String token);


}
