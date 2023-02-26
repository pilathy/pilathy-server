package com.pilathy.api.user.config.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String pattern;
    @Override
    public void initialize(Password constraintAnnotation) {
        this.pattern= constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)){
            return false;
        }

        return value.matches(pattern);
    }
}
