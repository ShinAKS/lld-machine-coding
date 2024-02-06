package dao;

import models.Message;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDao {

    private static MessageDao INSTANCE;

    Map<String, Map<User,Integer>> consumerOffSetMap;

    Map<String, List<Message>> topicMessage;

    private MessageDao(){
        this.consumerOffSetMap = new HashMap<>();
        this.topicMessage = new HashMap<>();
    }

    public static MessageDao getInstance(){
        if (INSTANCE == null) INSTANCE = new MessageDao();
        return INSTANCE;
    }

    public void addTopic(String topicName){
        if (topicMessage.containsKey(topicName)){
            return;
        }
        topicMessage.put(topicName,new ArrayList<>());
        consumerOffSetMap.put(topicName,new HashMap<>());
    }

    public void addConsumerToTopic(String topicName, User user){
        if (!consumerOffSetMap.containsKey(topicName)){
            return ;
        }
        if (consumerOffSetMap.get(topicName).containsKey(user)){
            return;
        }
        consumerOffSetMap.get(topicName).put(user,0);
    }

    public void addMessage(String topicName, Message message){
        if (!topicMessage.containsKey(topicName)){
            return;
        }
        List<Message> messagesForTopic = topicMessage.get(topicName);
        messagesForTopic.add(message);
    }

    public Message consumeMessage(String topicName, User user){
        if (!topicMessage.containsKey(topicName)
                || !consumerOffSetMap.containsKey(topicName)){
            return null;
        }
        if (consumerOffSetMap.containsKey(user)){
            return null;
        }
        int offset = consumerOffSetMap.get(topicName).getOrDefault(user,0);
        Message message = topicMessage.get(topicName).get(offset);
        if (message == null){
            System.out.println("No message to display");
            return null;
        }
        consumerOffSetMap.get(topicName).put(user,Math.min(offset+1,topicMessage.get(topicName).size()));
        return message;
    }

}
