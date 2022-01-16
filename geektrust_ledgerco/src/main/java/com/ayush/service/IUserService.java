package com.ayush.service;

public interface IUserService {

    void addLoan(String bankName, String user, double principal,double time, double rate);

    void addPayment(String bankName, String user, double amount, int emiNo);

    void showBalance(String bankName, String user, int emiNo);

}
