package com.example.oprs.annotation;

import com.example.oprs.annotation.impl.SecurityCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = SecurityCodeValidator.class)
@Documented
public @interface CheckSecurityCode {
        String message() default "Security code is not correct";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
