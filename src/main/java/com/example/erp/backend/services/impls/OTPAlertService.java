package com.example.erp.backend.services.impls;

import com.example.erp.backend.entities.OTPAlerts;

import java.time.LocalDateTime;

public interface OTPAlertService {
    boolean addOTP(String actionType, String otp, String email, String mobileNo, LocalDateTime expiryTime);

    OTPAlerts checkOTP(String actionType,String otp);
}
