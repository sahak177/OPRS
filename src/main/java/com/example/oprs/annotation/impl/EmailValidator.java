package com.example.oprs.annotation.impl;

import com.example.oprs.annotation.UniqueEmail;
import com.example.oprs.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null) {
            return true;
        }
        try {
            userService.getUserByEmail(email);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}