package com.ayush.dao;

import com.ayush.exceptions.UserExistsException;
import com.ayush.exceptions.UserNotFoundException;
import com.ayush.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static UserDao INSTANCE;

    private Map<String, User>userMap;

    private UserDao(){
        this.userMap = new HashMap<>();
    }

    public static UserDao getInstance(){
        if (INSTANCE==null){
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    public void addUser(User user){
        if (userMap.containsKey(user.getName())){
            throw new UserExistsException("User already exists!");
        }
        userMap.put(user.getName(),user);
    }

    public User getUser(String userName){
        if (!userMap.containsKey(userName)){
            throw new UserNotFoundException("User not found");
        }
        return this.userMap.get(userName);
    }
}
