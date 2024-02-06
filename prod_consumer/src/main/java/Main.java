import models.Message;
import service.ConsumerService;
import service.ProducerService;
import service.UserService;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserService();
        ProducerService producerService = new ProducerService();
        ConsumerService consumerService = new ConsumerService();

        int token1 = userService.addUser("user1");
        int token2 = userService.addUser("user2");

        userService.createTopic("user1",token1,"TOPIC1");
        userService.createTopic("user1",token1,"TOPIC2");

        userService.addConsumerToTopic("TOPIC1","user2");

        Message m11 = new Message("TOPIC1","hello message");
        Message m12 = new Message("TOPIC1","hi message");
        Message m21 = new Message("TOPIC2","hello message2");
        Message m22 = new Message("TOPIC2","hi message2");


        producerService.produceMessage("user1",token1,m11);

        consumerService.consumeMessage("user2",token2,"TOPIC1");
        producerService.produceMessage("user1",token1,m12);
        consumerService.consumeMessage("user2",token2,"TOPIC1");



        producerService.produceMessage("user1",token1,m21);
        producerService.produceMessage("user1",token1,m22);

        consumerService.consumeMessage("user2",token2,"TOPIC2");
        consumerService.consumeMessage("user2",token2,"TOPIC2");


        /*    Outputs:
                user: user2, message: Message(topic=TOPIC1, content=hello message)
                user: user2, message: Message(topic=TOPIC1, content=hi message)
                user: user2, message: Message(topic=TOPIC2, content=hello message2)
                user: user2, message: Message(topic=TOPIC2, content=hi message2)
         */

    }
}
