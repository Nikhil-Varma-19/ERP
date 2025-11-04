package com.example.erp.backend.exceptions;

public class DataNotFound extends  RuntimeException{
   public DataNotFound(String message){
       super(message);
   }
}
