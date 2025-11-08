package com.example.erp.backend.interceptors;

import com.example.erp.backend.dtos.logger_dtos.ApiLoggerDto;
import com.example.erp.backend.services.LoggerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiLoggerInterceptors implements HandlerInterceptor {

    @Autowired
    private  LoggerService loggerService;

    @Override
    public boolean  preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        ApiLoggerDto apiLoggerDto=new ApiLoggerDto();
        apiLoggerDto.setUrl(request.getRequestURI());
        apiLoggerDto.setMethod(request.getMethod());
        apiLoggerDto.setIpAddress(request.getRemoteAddr());
        String token=request.getHeader("Authorization") != null ? request.getHeader("Authorization").split(" ")[1] : null ;
        apiLoggerDto.setToken(token);
        loggerService.createApiLogger(apiLoggerDto);
        return  true;



    }

}
