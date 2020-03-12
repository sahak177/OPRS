package com.example.oprs.service;

import com.example.oprs.dao.User;
import com.example.oprs.dto.UserDto;

public interface UserService {

    UserDto getUserByEmail(String username);

    User getUserByEmailForSecurity(String username);

    boolean add(UserDto userDto);

    boolean addAsAdmin(UserDto officerDto, String officerRole);

    boolean updatePassword(String encodedNewPassword, String currentUser);

    boolean addToken(Long userId, String token);


}
