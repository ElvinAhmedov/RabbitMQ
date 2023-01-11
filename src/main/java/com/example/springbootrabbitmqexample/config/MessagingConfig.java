package com.example.springbootrabbitmqexample.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.JsonbMessageConverter;

@Configuration
public class MessagingConfig {
    public  static final String QUEUE = "shadow_queue";
    public static final String EXCHANGE = "shadow_exchange";
    public static final String ROUTING_KEY = "shadow_routingKey";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;

    }

}
