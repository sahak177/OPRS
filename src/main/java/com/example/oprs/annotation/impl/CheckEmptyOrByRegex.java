package com.example.oprs.annotation.impl;

import com.example.oprs.annotation.EmptyOrByRegex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmptyOrByRegex implements ConstraintValidator<EmptyOrByRegex, String> {

    EmptyOrByRegex emptyOrByRegex;

    @Override
    public void initialize(EmptyOrByRegex constraintAnnotation) {
        this.emptyOrByRegex = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        if (value.isEmpty()) {
            return true;
        }

        if (value.matches(emptyOrByRegex.regexp())) {
            return true;
        }
        return false;

    }
}
