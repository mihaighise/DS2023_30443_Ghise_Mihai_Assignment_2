package com.utcn.assignment2.Assignment2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Receiver {

    ArrayList<CustomMessage> receivedMessages = new ArrayList<>();

    Float currentHourlyConsumption = 0F;

    @RabbitListener(queues = "hello")
    public void receiveMessage(final CustomMessage customMessage) {
        receivedMessages.add(customMessage);
        if(receivedMessages.get(receivedMessages.size() - 1).timestamp().getHour() != receivedMessages.get(receivedMessages.size() - 2).timestamp().getHour()) {
            //TODO sent to database the currentHourlyConsumption
        }
        System.out.println("Received message and deserialized to 'CustomMessage': {}" + customMessage.toString());
    }
}
