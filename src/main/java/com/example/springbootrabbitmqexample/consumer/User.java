package com.example.springbootrabbitmqexample.consumer;

import com.example.springbootrabbitmqexample.config.MessagingConfig;
import com.example.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue : " + orderStatus);

    }
}
