package com.fk.dao;

import com.fk.exceptions.UserExistsException;
import com.fk.exceptions.UserNotFoundException;
import com.fk.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static UserDao userDaoInstance;

    private Map<String, User>userMap;

    public static UserDao getInstance(){
        if (userDaoInstance==null)userDaoInstance = new UserDao();
        return userDaoInstance;
    }

    private UserDao(){
        userMap = new HashMap<>();
    }

    public void addUser(User user){
        if (this.userMap.containsKey(user.getName())){
            throw new UserExistsException();
        }
        userMap.put(user.getName(),user);
    }

    public User getUser(String userName){
        if (!this.userMap.containsKey(userName)){
            throw new UserNotFoundException();
        }
        return userMap.get(userName);
    }
}
