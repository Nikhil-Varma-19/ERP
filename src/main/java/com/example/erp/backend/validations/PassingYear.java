package com.example.erp.backend.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PassingYear implements ConstraintValidator<ValidPassingYear,Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null) return  false;
        return value >= 1947 && value <= LocalDate.now().getYear();
    }
}
