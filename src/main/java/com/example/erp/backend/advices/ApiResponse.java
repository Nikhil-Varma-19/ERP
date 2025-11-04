package com.example.erp.backend.advices;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private UUID traceId;


    public static <T>  ApiResponse<T> success(T data){
        return  ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T>  ApiResponse<T> success(String message){
        return  ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .build();
    }

    public static  <T> ApiResponse<T> error(String message){
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    public static  <T> ApiResponse<T> error(String message, UUID traceId){
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .traceId(traceId)
                .build();
    }
}
