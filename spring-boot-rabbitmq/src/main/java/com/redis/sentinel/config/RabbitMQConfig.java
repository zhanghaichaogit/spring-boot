package com.redis.sentinel.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhanghaichao on 2017/5/24.
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

}
