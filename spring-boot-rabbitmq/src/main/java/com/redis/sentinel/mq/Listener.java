package com.redis.sentinel.mq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration

@RabbitListener(queues = "hello")

public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}