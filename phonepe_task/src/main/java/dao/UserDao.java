package dao;

import exceptions.InvalidAccessException;
import exceptions.UserExistsException;
import exceptions.UserNotFoundException;
import models.Task;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {

    public static UserDao userDaoInstance;
    private Map<Integer, User> userMap;
    private Map<Integer, List<Task>> userTasks;
    private static int userIdCounter;

    /*

        Task
            - id
            -

        User
            - id

        User_task
            - userId
            - taskId
     */

    private UserDao(){
        this.userMap = new HashMap<>();
        userIdCounter = 0;
    }

    public static UserDao getUserDaoInstance(){
        if (userDaoInstance == null){
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    public boolean addUser(User user) throws UserExistsException {
        if (this.userMap.containsKey(user.getUserId())){
            throw new UserExistsException();
        }
        this.userMap.put(user.getUserId(),user);
        return true;
    }

    public User loginUser(String userId, String password) throws UserNotFoundException, InvalidAccessException {
        if (!this.userMap.containsKey(userId)){
            throw new UserNotFoundException();
        }
        User user = this.userMap.get(userId);
        if (!user.getPassword().equals(password)){
            throw new InvalidAccessException();
        }
        return user;
    }

    public User getUser(Integer userId){
        return this.userMap.get(userId);
    }
}
