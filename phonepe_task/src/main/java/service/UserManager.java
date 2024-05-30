package service;

import dao.UserDao;
import exceptions.InvalidAccessException;
import exceptions.UserExistsException;
import exceptions.UserNotFoundException;
import models.Task;
import models.User;

import java.util.List;
import java.util.Map;

public class UserManager {

    private UserDao userDao;

    public UserManager(){
        this.userDao = UserDao.getUserDaoInstance();
    }

    public void createUser(Integer userid, String password){
        User user = User.builder()
                .userId(userid)
                .password(password)
                .build();

        try {
            this.userDao.addUser(user);
            System.out.println("Successfully created user : " + user);
        } catch (UserExistsException e) {
            System.out.println("User already exists");
        }
    }

    public void loginUser(String userId, String password){
        try {
            User user = this.userDao.loginUser(userId,password);
            System.out.println("Found user: " + user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidAccessException e) {
            System.out.println("Invalid login credentials");
        }
    }
}
