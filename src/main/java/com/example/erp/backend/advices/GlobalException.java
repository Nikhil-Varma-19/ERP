package com.example.erp.backend.advices;


import com.example.erp.backend.dtos.ErrorLoggerDto;
import com.example.erp.backend.services.LoggerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    @Autowired
    private LoggerService loggerService;


    @ExceptionHandler(AccessDeniedException.class)
    public  ResponseEntity<ApiResponse<?>> accessDenied(AccessDeniedException ex){
        return  new ResponseEntity<>(ApiResponse.error("Access Denied"),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<ApiResponse<?>> authenticationError(Authentication ex){
        return  new ResponseEntity<>(ApiResponse.error("Invalid Username and Password"),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ApiResponse<?>> validationFailed(MethodArgumentNotValidException ex){
        List<String>  errors=ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return  new ResponseEntity<>(ApiResponse.error(errors.getFirst()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnexpectedExceptions(Exception ex, HttpServletRequest req){
        UUID traceId=UUID.randomUUID();
        ErrorLoggerDto errorLoggerDto=new ErrorLoggerDto();
        errorLoggerDto.setUrl(req.getRequestURI());
        errorLoggerDto.setMessage(ex.getMessage());
        errorLoggerDto.setMethod(req.getMethod());
        errorLoggerDto.setTraceId(traceId);
        System.out.println(traceId);
        loggerService.createErrorLog(errorLoggerDto);

        return  new ResponseEntity<>(ApiResponse.error("Something went wrong.",traceId), HttpStatus.INTERNAL_SERVER_ERROR);



    }

}
