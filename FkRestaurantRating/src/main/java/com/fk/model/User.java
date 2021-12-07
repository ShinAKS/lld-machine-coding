package com.fk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;

//    Map<Restaurant,Rating>
    public User(String name){
        this.name = name;
    }

}
