package com.example.BlogApplication.handler;


import com.example.BlogApplication.exception.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>resourceNotFoundExceptionHandler(Exception ex){
        Map<String,String>resultMap = Map.of("exception",ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>methodArgumentExceptionHandling(MethodArgumentNotValidException ex){
        Map<String,Object>resultMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            FieldError fieldError = (FieldError)error;
            String errMsg = fieldError.getDefaultMessage();
            String fieldName = fieldError.getField();
            resultMap.put(fieldName,errMsg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
    }

}
