package com.example.erp.backend.dtos;

import com.example.erp.backend.enums.Role;
import lombok.Data;
import java.util.Set;

@Data
public class LoginResponseDto {
    private String token;
//    private  String refreshToken;
    private Set<Role> role;
}
