package com.example.erp.backend.advices;


import com.example.erp.backend.dtos.logger_dtos.ErrorLoggerDto;
import com.example.erp.backend.exceptions.*;
import com.example.erp.backend.services.LoggerService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    @Autowired
    private LoggerService loggerService;

    @ExceptionHandler(NoData.class)
    public  ResponseEntity<ApiResponse<String>> noData(NoData ex){
        return  new ResponseEntity<>(ApiResponse.success(ex.getMessage()),HttpStatus.OK);
    }

    @ExceptionHandler(BadRequest.class)
    public  ResponseEntity<ApiResponse<String>> badRequest(BadRequest ex){
        return  new ResponseEntity<>(ApiResponse.success(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingPart(MissingServletRequestPartException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error("Missing required file part: " + ex.getRequestPartName()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> urlNotFound(NoResourceFoundException ex){
        return  ResponseEntity.badRequest().body(ApiResponse.error("Url Not Found"));
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ApiResponse<?>> emailError(EmailException ex){
        return  new ResponseEntity<>(ApiResponse.error(ex.getMessage()),HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ApiResponse<?>> stringToDto(FileException ex){
        return new  ResponseEntity<>(ApiResponse.error(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AccessDeniedException.class,AuthorizationDeniedException.class})
    public  ResponseEntity<ApiResponse<?>> accessDenied( Exception ex){
        return  new ResponseEntity<>(ApiResponse.error("Access Denied"),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(DataNotFound ex){
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<?>> handleFileError(IOException ex) {
        return new ResponseEntity<>(ApiResponse.error("File operation failed: " + ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
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
        List<String>  errors=ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return  new ResponseEntity<>(ApiResponse.error(errors.getFirst()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public  ResponseEntity<ApiResponse<?>> validationFailed(MethodArgumentTypeMismatchException ex){
        String res= "id".equals(ex.getName()) ? "Invalid ID. ID must be a number." : ex.getMessage();
        return  new ResponseEntity<>(ApiResponse.error(res),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ApiResponse.error(ex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnexpectedExceptions(Exception ex, HttpServletRequest req){
        UUID traceId=UUID.randomUUID();
        ErrorLoggerDto errorLoggerDto=new ErrorLoggerDto();
        errorLoggerDto.setUrl(req.getRequestURI());
        errorLoggerDto.setMessage(ex.getMessage() + " / " + ex.getClass().getName());
        errorLoggerDto.setMethod(req.getMethod());
        errorLoggerDto.setTraceId(traceId);
        System.out.println(traceId);
        loggerService.createErrorLog(errorLoggerDto);

        return  new ResponseEntity<>(ApiResponse.error("Something went wrong.",traceId), HttpStatus.INTERNAL_SERVER_ERROR);



    }

}
