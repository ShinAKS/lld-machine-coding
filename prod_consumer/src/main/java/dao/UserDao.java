package dao;

import exceptions.UserExistsException;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao {
    private static UserDao INSTANCE;

    Map<String, User> userMap;

    private UserDao(){
        this.userMap = new HashMap<>();
    }

    public static UserDao getUserDaoInstance(){
        if (INSTANCE==null) INSTANCE = new UserDao();
        return INSTANCE;
    }

    public int addUser(String userId) throws UserExistsException {
        if (this.userMap.containsKey(userId)){
            throw new UserExistsException("UserId : " + userId + " already exists");
        }
        User user = new User(userId);
        this.userMap.put(userId, user);
        return user.getTokenId();
    }

    public Optional<User> getUser(String userId){
        User user = this.userMap.get(userId);
        return Optional.of(user);
    }

    public List<User> listUsers(){
        return userMap.values().stream().toList();
    }
}
