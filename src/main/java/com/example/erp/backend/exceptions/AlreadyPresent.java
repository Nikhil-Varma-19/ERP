package com.example.erp.backend.exceptions;

public class AlreadyPresent extends  RuntimeException{
    public AlreadyPresent(String message){
        super(message);
    }
}
