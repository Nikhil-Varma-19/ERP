package com.example.erp.backend.dtos.logger_dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailLoggerDto {
    private String toMail;
    private String others;
    private String body;
    private String subject;
    private String error;
}
