package com.ayush.mymoney.exceptions;

public class InvalidCommandException extends RuntimeException{

    private String message;

    public InvalidCommandException(String message){
        super(message);
    }
}
