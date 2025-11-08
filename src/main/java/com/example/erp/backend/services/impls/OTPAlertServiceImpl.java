package com.example.erp.backend.services.impls;

import com.example.erp.backend.entities.OTPAlerts;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.repositories.OTPAlertResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OTPAlertServiceImpl implements OTPAlertService{
    @Autowired
    private OTPAlertResp otpAlertResp;

    @Override
    public boolean addOTP(String actionType, String otp, String email, String mobileNo, LocalDateTime expiryTime) {

        if (actionType == null || actionType.isBlank()) {
            throw new DataNotFound("Action type is required.");
        }
        if (otp == null || otp.isBlank()) {
            throw new DataNotFound("OTP is required.");
        }
        if (expiryTime == null) {
            throw new DataNotFound("Expiry time is required.");
        }

        if ((email == null || email.isBlank()) && (mobileNo == null || mobileNo.isBlank())) {
            throw new DataNotFound("Either email or mobile number must be provided.");
        }

        OTPAlerts otpAlerts=OTPAlerts.builder()
                .actionType(actionType).email(email).expiryTime(expiryTime).mobileNumber(mobileNo).otp(otp)
                .build();

        OTPAlerts otpSaved=otpAlertResp.save(otpAlerts);

        return otpSaved.getId() == null ? false : true;
    }
}
