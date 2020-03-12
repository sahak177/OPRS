package com.example.oprs.service.impl;

import com.example.oprs.dao.Role;
import com.example.oprs.dao.User;
import com.example.oprs.dto.UserDto;
import com.example.oprs.enums.RoleType;
import com.example.oprs.mappers.map.dto.dao.UserMapper;
import com.example.oprs.repository.UserRepository;
import com.example.oprs.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto getUserByEmail(String username) {
        User user = userRepository.getUserByEmail(username);
        userMapper.map(user, UserDto.class);
        return userMapper.map(user, UserDto.class);
    }

    @Override
    public User getUserByEmailForSecurity(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public boolean add(UserDto userDto) {
        User user = userMapper.map(userDto, User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public boolean addAsAdmin(UserDto officerDto, String officerRole) {
        User officer = userMapper.map(officerDto, User.class);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.valueOf(officerRole).name()));
        roles.add(new Role(RoleType.ROLE_USER.name()));
        officer.setRoles(roles);
        String encodedPassword = passwordEncoder.encode(officer.getPassword());
        officer.setPassword(encodedPassword);
        return userRepository.saveFromAdmin(officer);
    }

    @Override
    public boolean updatePassword(String encodedNewPassword, String currentUser) {
        return userRepository.updatePassword(encodedNewPassword, currentUser);

    }


    @Override
    public boolean addToken(Long userId, String token) {
        return userRepository.addToken(userId, token);
    }


}
