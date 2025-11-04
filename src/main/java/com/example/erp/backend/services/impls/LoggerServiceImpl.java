package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.ErrorLoggerDto;
import com.example.erp.backend.entities.ApiLogger;
import com.example.erp.backend.entities.ErrorLogger;
import com.example.erp.backend.mapper.LoggerMapper;
import com.example.erp.backend.repositories.ApiLoggerRep;
import com.example.erp.backend.repositories.ErrorLoggerRep;
import com.example.erp.backend.services.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    private  final ErrorLoggerRep errorLoggerRep;
    private final ApiLoggerRep apiLoggerRep;
    private final LoggerMapper loggerMapper;


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
}
