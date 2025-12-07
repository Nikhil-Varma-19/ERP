package com.example.erp.backend.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ResumeType.class)
public @interface VaildResumeType {

    String message() default "Invalid Resume Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
