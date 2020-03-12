package com.example.oprs.annotation.impl;

import com.example.oprs.annotation.CheckSecurityCode;
import com.example.oprs.service.SecurityCaptchaService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class SecurityCodeValidator implements ConstraintValidator<CheckSecurityCode, String> {
    private final SecurityCaptchaService securityCaptchaService;

    @Override
    public boolean isValid(String securityCode, ConstraintValidatorContext constraintValidatorContext) {
        if (securityCode == null) {
            return false;
        }
        try {
            securityCaptchaService.checkSecurityCode(securityCode);
            return true;
        } catch (Exception e) {
            return false;

        }
    }
}
