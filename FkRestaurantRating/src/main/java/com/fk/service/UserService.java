package com.fk.service;

import com.fk.dao.UserDao;
import com.fk.exceptions.UserExistsException;
import com.fk.exceptions.UserNotFoundException;
import com.fk.model.User;

import java.util.List;

public class UserService implements IUserService{

    private UserDao userDao;

    public UserService(){
        this.userDao = UserDao.getInstance();
    }
    @Override
    public void addUser(String userName) {
        try {
            this.userDao.addUser(new User(userName));
        }catch(UserExistsException e){
            System.out.println("User already exists");
        }
    }

    @Override
    public User getUser(String userName) {
        try{
            return userDao.getUser(userName);
        }catch(UserNotFoundException e){
            throw e;
        }
//        return null;
    }


}
