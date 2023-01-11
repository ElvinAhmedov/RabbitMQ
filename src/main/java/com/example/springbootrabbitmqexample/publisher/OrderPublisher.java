package com.example.springbootrabbitmqexample.publisher;

import com.example.springbootrabbitmqexample.config.MessagingConfig;
import com.example.springbootrabbitmqexample.dto.Order;
import com.example.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus =
                new OrderStatus(order, "Process", "order placed succesfully in" + restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "success!";
    }

}
