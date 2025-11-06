package com.example.erp.backend.dtos;

import com.example.erp.backend.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserDto {
    @Email(message = "Invalid email format")
    private String email;

    private String name;
}
