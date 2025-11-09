package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.LoginBodyDto;
import com.example.erp.backend.dtos.LoginResponseDto;
import com.example.erp.backend.dtos.RestPasswordDto;
import com.example.erp.backend.entities.EmailTemplate;
import com.example.erp.backend.entities.OTPAlerts;
import com.example.erp.backend.entities.User;
import com.example.erp.backend.enums.ActionType;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.filters.JwtFilter;
import com.example.erp.backend.repositories.EmailTemplateResp;
import com.example.erp.backend.repositories.UserRep;
import com.example.erp.backend.services.AuthService;
import com.example.erp.backend.utilizs.EmailServices;
import com.example.erp.backend.utilizs.OTPUtiliz;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRep userRep;
    private final JwtFilter jwtFilter;
    private final EmailTemplateResp emailTemplateResp;
    private final EmailServices emailServices;
    private final OTPAlertService otpAlertService;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public String forgotPassword(String email) {
        User user=userRep.findByEmailAndIsActiveTrue(email).orElseThrow(()-> new DataNotFound("Email not found") );
        EmailTemplate emailTemplate=emailTemplateResp.findByEventAndIsActiveTrue("forgotPassword").orElseThrow(()-> new DataNotFound("Template Not found"));
        String otpGen= OTPUtiliz.generateOtp();
        String text=emailTemplate.getContent().replaceAll("<otp>",otpGen);
        text=text.replaceAll("<name>", user.getName());
        String subject=emailTemplate.getSubject();
        String[] to={user.getEmail()};
        LocalDateTime expiryTime=LocalDateTime.now().plusMinutes(5);
        boolean savedData= otpAlertService.addOTP(String.valueOf(ActionType.FORGOT_PASSWORD),otpGen, user.getEmail(), null,expiryTime);
       if(!savedData){
           return  "SomeThing Wrong";
       }
        emailServices.sendEmail(to,subject,text,null);
        return "Mail is Send Successfully.";
    }

    @Override
    @Transactional
    public String resetPassword(RestPasswordDto restPasswordDto) {
      OTPAlerts otpAlerts=otpAlertService.checkOTP(String.valueOf(ActionType.FORGOT_PASSWORD),restPasswordDto.getOtp());
      User user=userRep.findByEmailAndIsActiveTrue(otpAlerts.getEmail()).orElseThrow(()-> new DataNotFound("User Not found"));
      user.setPassword(passwordEncoder.encode(restPasswordDto.getPassword()));
      userRep.save(user);
        return "Password is Successfully Updated";
    }


}
