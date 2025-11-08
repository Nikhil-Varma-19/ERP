package com.example.erp.backend.services;

import com.example.erp.backend.dtos.LoginBodyDto;
import com.example.erp.backend.dtos.LoginResponseDto;
import jakarta.servlet.http.Cookie;

public interface AuthService {

    LoginResponseDto login(LoginBodyDto loginBodyDto);

    String refreshToken(Cookie[] cookie);

    String forgotPassword(String email);
}
