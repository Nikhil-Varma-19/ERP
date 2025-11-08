package com.example.erp.backend.dtos.logger_dtos;

import lombok.Data;

@Data
public class ApiLoggerDto {
    private String url;
    private String method;
    private String body;
    private String token;
    private String ipAddress;
}
