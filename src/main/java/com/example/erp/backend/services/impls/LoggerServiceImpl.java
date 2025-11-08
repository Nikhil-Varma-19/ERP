package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.logger_dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.EmailLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.ErrorLoggerDto;
import com.example.erp.backend.entities.ApiLogger;
import com.example.erp.backend.entities.EmailLogger;
import com.example.erp.backend.entities.ErrorLogger;
import com.example.erp.backend.mapper.LoggerMapper;
import com.example.erp.backend.repositories.ApiLoggerRep;
import com.example.erp.backend.repositories.EmailLoggerResp;
import com.example.erp.backend.repositories.ErrorLoggerRep;
import com.example.erp.backend.services.LoggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    private  final ErrorLoggerRep errorLoggerRep;
    private final ApiLoggerRep apiLoggerRep;
    private final LoggerMapper loggerMapper;
    private final EmailLoggerResp emailLoggerResp;


    @Override
    public void createErrorLog(ErrorLoggerDto errorLoggerDto) {
        System.out.println("DYOODOODODODODO "+errorLoggerDto);
        ErrorLogger errorLogger=loggerMapper.dtoToError(errorLoggerDto);
        errorLoggerRep.save(errorLogger);
    }

    @Override
    public void createApiLogger(ApiLoggerDto apiLoggerDto) {
        ApiLogger apiLogger=loggerMapper.dtoToApi(apiLoggerDto);
        apiLoggerRep.save(apiLogger);

    }

    @Override
    public void createEmailLogger(EmailLoggerDto emailLoggerDto) {
        EmailLogger emailLogger=loggerMapper.dtoToEmail(emailLoggerDto);
        emailLoggerResp.save(emailLogger);
    }
}
