package com.example.erp.backend.mapper;


import com.example.erp.backend.dtos.ApiLoggerDto;
import com.example.erp.backend.dtos.ErrorLoggerDto;
import com.example.erp.backend.entities.ApiLogger;
import com.example.erp.backend.entities.ErrorLogger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoggerMapper {

    ApiLogger dtoToApi(ApiLoggerDto apiLoggerDto);

    ErrorLogger dtoToError(ErrorLoggerDto errorLoggerDto);

}
