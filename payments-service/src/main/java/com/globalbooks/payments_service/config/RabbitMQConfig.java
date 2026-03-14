package com.globalbooks.payments_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_QUEUE = "orderQueue";

    @Bean
    public Queue queue() {
        return new Queue(ORDER_QUEUE);
    }
}