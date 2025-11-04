package com.example.erp.backend.services;

import com.example.erp.backend.dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.ErrorLoggerDto;

public interface LoggerService {
    void createErrorLog(ErrorLoggerDto errorLoggerDto);

    void createApiLogger(ApiLoggerDto apiLoggerDto);
}