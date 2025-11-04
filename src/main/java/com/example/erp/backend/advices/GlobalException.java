package com.example.erp.backend.advices;


import com.example.erp.backend.dtos.ErrorLoggerDto;
import com.example.erp.backend.exceptions.AlreadyPresent;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.services.LoggerService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    @Autowired
    private LoggerService loggerService;


    @ExceptionHandler({AccessDeniedException.class,AuthorizationDeniedException.class})
    public  ResponseEntity<ApiResponse<?>> accessDenied( Exception ex){
        return  new ResponseEntity<>(ApiResponse.error("Access Denied"),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(DataNotFound ex){
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyPresent.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyPresent(AlreadyPresent ex){
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyPresent(JwtException ex){
        return new ResponseEntity<>(ApiResponse.error("Invalid token."),HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<ApiResponse<?>> authenticationError(AuthenticationException ex){
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
