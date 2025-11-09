package com.example.erp.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestPasswordDto {
    @NotBlank(message = "Email is required.")
    private String password;

    @NotBlank(message = "OTP is required.")
    private String otp;
}
