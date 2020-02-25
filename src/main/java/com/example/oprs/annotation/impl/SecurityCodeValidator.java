package com.example.oprs.annotation.impl;

import com.example.oprs.annotation.CheckSecurityCode;
import com.example.oprs.service.SecurityService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class SecurityCodeValidator implements ConstraintValidator<CheckSecurityCode, String> {
    private final SecurityService securityService;

    @Override
    public boolean isValid(String securityCode, ConstraintValidatorContext constraintValidatorContext) {
        if (securityCode == null) {
            return false;
        }
        try {
            securityService.checkSecurityCode(securityCode);
            return true;
        } catch (Exception e) {
            return false;

        }
    }
}
