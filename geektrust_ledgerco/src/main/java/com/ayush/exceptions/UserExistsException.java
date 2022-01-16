package com.ayush.exceptions;

public class UserExistsException extends RuntimeException{

    private String message;
    public UserExistsException(String message){
        super(message);
    }
}
