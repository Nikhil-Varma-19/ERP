package com.example.erp.backend.mapper;


import com.example.erp.backend.dtos.logger_dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.EmailLoggerDto;
import com.example.erp.backend.dtos.logger_dtos.ErrorLoggerDto;
import com.example.erp.backend.entities.ApiLogger;
import com.example.erp.backend.entities.EmailDetail;
import com.example.erp.backend.entities.EmailLogger;
import com.example.erp.backend.entities.ErrorLogger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoggerMapper {

    ApiLogger dtoToApi(ApiLoggerDto apiLoggerDto);

    ErrorLogger dtoToError(ErrorLoggerDto errorLoggerDto);

    EmailLogger dtoToEmail(EmailLoggerDto emailLoggerDto);

}
