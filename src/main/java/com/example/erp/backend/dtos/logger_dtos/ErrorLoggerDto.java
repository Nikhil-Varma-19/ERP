package com.example.erp.backend.dtos.logger_dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ErrorLoggerDto {
    private String url;
    private String method;
    private String body;
    private String message;
    private UUID traceId;

}
