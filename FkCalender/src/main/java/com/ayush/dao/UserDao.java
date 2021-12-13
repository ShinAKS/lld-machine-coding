package com.ayush.dao;

import com.ayush.exception.UserExistsException;
import com.ayush.exception.UserNotFoundException;
import com.ayush.models.TimeSlot;
import com.ayush.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static UserDao INSTANCE;

    private Map<String, User> userMap;

    private UserDao(){
        this.userMap = new HashMap<>();
    }

    public static UserDao getInstance(){
        if (INSTANCE==null)INSTANCE = new UserDao();
        return INSTANCE;
    }

    public void addUser(String username, Date start, Date end){
        if (userMap.containsKey(username)){
            throw new UserExistsException();
        }
        User user = new User(username,start,end);
        userMap.put(username, user);

        System.out.println("User " + username  + " added");
    }

    public User getUser(String username){
        if (!userMap.containsKey(username)){
            throw new UserNotFoundException();
        }
        return userMap.get(username);
    }
}
