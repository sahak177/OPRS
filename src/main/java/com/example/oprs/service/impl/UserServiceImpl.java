package com.example.oprs.service.impl;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.Role;
import com.example.oprs.pojo.RoleType;
import com.example.oprs.pojo.User;
import com.example.oprs.repository.UserRepository;
import com.example.oprs.service.UserService;
import com.example.oprs.util.TextToGraphicConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User getUserByEmail(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public boolean add(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public boolean addAsAdmin(User officer, String officerRole) {
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

    public void validateInput(User user) throws InValidInputException {

        if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
            throw new InValidInputException("invalid password");
        }

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern patternEmail = Pattern.compile(regex);
        Matcher matcherEmail = patternEmail.matcher(user.getEmail());
        if (!matcherEmail.matches()) {
            throw new InValidInputException("invalid email");
        }
        String regexName = "[A-Z][a-z]*";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherFName = patternName.matcher(user.getFirstName());

        if (!matcherFName.matches() || user.getFirstName().length() < 2 || user.getFirstName().length() > 20) {
            throw new InValidInputException("invalid First Name");
        }

        Matcher matcherLName = patternName.matcher(user.getLastName());
        if (!matcherLName.matches() || user.getLastName().length() < 2 || user.getLastName().length() > 20) {
            throw new InValidInputException("invalid Last Name");
        }

        Pattern patternNumber = Pattern.compile("\\d{9}");
        Matcher matcherNumber = patternNumber.matcher(user.getSocialSecurityNumber());

        if (!matcherNumber.matches()) {
            throw new InValidInputException("invalid Social Number");
        }
    }

    @Override
    public boolean addToken(Long userId, String token) {
        return userRepository.addToken(userId, token);
    }


}
