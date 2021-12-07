package com.fk.service;

import com.fk.model.User;

import java.util.List;

public interface IUserService {

    void addUser(String userName);

    User getUser(String userName);

}
