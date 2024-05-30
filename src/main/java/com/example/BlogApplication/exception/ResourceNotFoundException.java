package com.example.BlogApplication.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){
        super("User Id not found on the server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
