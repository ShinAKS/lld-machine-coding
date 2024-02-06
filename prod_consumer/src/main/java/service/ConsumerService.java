package service;

import dao.MessageDao;
import models.Message;
import models.User;

public class ConsumerService {

    private UserService userService;
    public MessageDao messageDao;

    public ConsumerService(){
        this.userService = new UserService();
        this.messageDao = MessageDao.getInstance();
    }

    public void consumeMessage(String userId, int tokenId , String topicName){
        User user = userService.getUser(userId);
        if (user==null || user.getTokenId()!=tokenId){
            return;
        }
        Message message = messageDao.consumeMessage(topicName,user);
        System.out.println("user: " + userId + ", message: " + message);
    }
}
