package com.example.erp.backend.controllers;


import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.LoginBodyDto;
import com.example.erp.backend.dtos.LoginResponseDto;
import com.example.erp.backend.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginBodyDto loginBodyDto){
        LoginResponseDto loginResponseDto=authService.login(loginBodyDto);
        return  ResponseEntity.ok(ApiResponse.success(loginResponseDto));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<ApiResponse<?>> refreshToken(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        String token= authService.refreshToken(cookies);
        Map<String, String> map=new HashMap<>();
       map.put("token",token);
      return  ResponseEntity.ok(ApiResponse.success(map));
    }



}
