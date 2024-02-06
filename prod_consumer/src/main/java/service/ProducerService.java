package service;

import dao.MessageDao;
import models.Message;
import models.User;

public class ProducerService {

    private UserService userService;
    public MessageDao messageDao;

    public ProducerService(){
        this.userService = new UserService();
        this.messageDao = MessageDao.getInstance();
    }

    public void produceMessage(String userId, int tokenId, Message message){
        User user = userService.getUser(userId);
        if (user==null || user.getTokenId()!=tokenId){
            return;
        }
        this.messageDao.addMessage(message.getTopic(),message);
    }

}
