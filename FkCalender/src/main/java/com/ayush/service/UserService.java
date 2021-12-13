package com.ayush.service;

import com.ayush.dao.UserDao;
import com.ayush.models.Event;
import com.ayush.models.User;

public class UserService {

    private UserDao userDao;

    public UserService(){
        this.userDao = UserDao.getInstance();
    }

    public User getUser(String name){
        return userDao.getUser(name);
    }

    public void addEvent(User user, Event event){
        user.getEvents().add(event);
    }


}
