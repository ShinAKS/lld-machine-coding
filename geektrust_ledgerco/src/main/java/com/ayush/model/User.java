package com.ayush.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

    private String name;

    private List<Account> accountList;

    public User(String name){
        this.name = name;
        this.accountList = new ArrayList<>();
    }
}
