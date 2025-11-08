package com.example.erp.backend.utilizs;

import java.security.SecureRandom;

public class OTPUtiliz {

    private static final int otpLength=6;
    private static final SecureRandom secureRandom=new SecureRandom();
    private static final String digit ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private OTPUtiliz(){}

    public static String generateOtp(int otpLength){

        StringBuilder otp=new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(digit.charAt(secureRandom.nextInt(digit.length())));
        }
        return  otp.toString();
    }

    public static String generateOtp(){
        return generateOtp(otpLength);
    }


}
