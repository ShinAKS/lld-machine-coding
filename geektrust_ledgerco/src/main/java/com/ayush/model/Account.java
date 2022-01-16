package com.ayush.model;

import lombok.Getter;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
public class Account {

    private String bankName;

    private double principal;

    private double rate;

    private double time;

    private double amount;

    private int emiValue;

    private TreeSet<Lumpsum> paidAmounts;

    public Account(String bankName,double principal,double rate,double time){
        this.bankName = bankName;
        this.principal = principal;
        this.rate = rate;
        this.time = time;
        this.amount = initializeAmount(principal,rate,time);
        this.emiValue = initializeEmi(this.amount,time);
        this.paidAmounts = new TreeSet<>();
    }

    public double initializeAmount(double p,double r, double t){
        return p + (p*r*t)/100.0;
    }

    public int initializeEmi(double amount, double time){

        return (int) Math.ceil((amount/(12*time)));
    }
}
