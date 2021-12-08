package com.ayush.mymoney.exceptions;

public class PortfolioExistsException extends RuntimeException{

    public PortfolioExistsException(String message){
        super(message);
    }
}
