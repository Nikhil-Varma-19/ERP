package com.example.erp.backend.services;

import com.example.erp.backend.dtos.logger_dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.EmailLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.ErrorLoggerDto;

public interface LoggerService {
    void createErrorLog(ErrorLoggerDto errorLoggerDto);

    void createApiLogger(ApiLoggerDto apiLoggerDto);

    void createEmailLogger(EmailLoggerDto emailLoggerDto);
}