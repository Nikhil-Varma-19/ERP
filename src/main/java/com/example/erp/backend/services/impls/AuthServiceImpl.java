package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.LoginBodyDto;
import com.example.erp.backend.dtos.LoginResponseDto;
import com.example.erp.backend.entities.User;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.filters.JwtFilter;
import com.example.erp.backend.repositories.UserRep;
import com.example.erp.backend.services.AuthService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRep userRep;
    private final JwtFilter jwtFilter;




    @Override
    public LoginResponseDto login(LoginBodyDto loginBodyDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBodyDto.getEmail(),loginBodyDto.getPassword()));
        User user=(User) authentication.getPrincipal();
        String jwtToken=jwtFilter.generateJWTToken(user);
//        String jwtRefreshToken= jwtFilter.generateRefreshToken(user);
        LoginResponseDto loginResponseDto=new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
//        loginResponseDto.setRefreshToken(jwtRefreshToken);
        loginResponseDto.setRole(user.getRoles());
        return loginResponseDto;
    }

    @Override
    public String refreshToken(Cookie[] cookies) {
        String key = "refreshToken";
        String tokenFind = null;
        if (cookies == null || cookies.length < 1)
            throw new DataNotFound("Cannot access the refresh token");
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                tokenFind = cookie.getValue();
                break;
            }
        }
        if (tokenFind == null) throw new DataNotFound("Cannot access the refresh token");
        Long id= jwtFilter.getIdFromRefreshToken(tokenFind);
        Optional<User> user=userRep.findByIdAndIsActiveTrue(id);
        if(user.isEmpty()) throw  new DataNotFound("User Not Found");
        return jwtFilter.generateJWTToken(user.get());
    }


}
