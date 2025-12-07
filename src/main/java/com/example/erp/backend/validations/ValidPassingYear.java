package com.example.erp.backend.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PassingYear.class)
public @interface ValidPassingYear {
    String message() default "Invalid Year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

