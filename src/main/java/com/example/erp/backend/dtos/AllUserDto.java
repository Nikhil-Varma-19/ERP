package com.example.erp.backend.dtos;

import com.example.erp.backend.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class AllUserDto {
    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;
}
