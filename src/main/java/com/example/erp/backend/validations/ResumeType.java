package com.example.erp.backend.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ResumeType implements ConstraintValidator<VaildResumeType,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isBlank()) return false;
        return Arrays.stream(com.example.erp.backend.enums.ResumeType.values())
                .anyMatch(type -> type.name().equals(value) );
    }
}
