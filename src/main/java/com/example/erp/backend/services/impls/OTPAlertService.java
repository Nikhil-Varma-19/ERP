package com.example.erp.backend.services.impls;

import java.time.LocalDateTime;

public interface OTPAlertService {
    boolean addOTP(String actionType, String otp, String email, String mobileNo, LocalDateTime expiryTime);

}
