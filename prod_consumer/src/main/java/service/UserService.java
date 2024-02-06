package service;

import dao.MessageDao;
import dao.UserDao;
import models.User;

import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private MessageDao messageDao;

    public UserService(){
        this.userDao = UserDao.getUserDaoInstance();
        this.messageDao = MessageDao.getInstance();
    }

    public int addUser(String userId){
        int tokenId = -1;
        try{
            tokenId = this.userDao.addUser(userId);
        } catch (Exception ex){
            System.out.println("Couldn't register user: " + userId + " ,exception: " + ex);
        }
        return tokenId;
    }

    public User getUser(String userId){
        return this.userDao.getUser(userId).get();
    }

    public void addConsumerToTopic(String topic, String userid){
        User user = getUser(userid);
        messageDao.addConsumerToTopic(topic,user);
    }
    public void createTopic(String userId, int tokenId, String topicName){
        Optional<User> user = this.userDao.getUser(userId);
        if (user.isPresent()){
            if (tokenId != user.get().getTokenId()){
                return;
            }
            messageDao.addTopic(topicName);
        }
    }

}
