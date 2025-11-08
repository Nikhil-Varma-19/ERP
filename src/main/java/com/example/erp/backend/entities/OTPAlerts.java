package com.example.erp.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "otp_alerts")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OTPAlerts extends DBCommon{

    private String otp;

    private String email;

    private String mobileNumber;

    private LocalDateTime expiryTime;

    private String actionType;
}
