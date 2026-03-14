package com.globalbooks.shipping_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String SHIPPING_QUEUE = "shippingQueue";
    public static final String SHIPPING_EXCHANGE = "shippingExchange";
    public static final String SHIPPING_ROUTING_KEY = "shippingRoutingKey";

    @Bean
    public Queue shippingQueue() {
        return new Queue(SHIPPING_QUEUE, true);
    }

    @Bean
    public DirectExchange shippingExchange() {
        return new DirectExchange(SHIPPING_EXCHANGE);
    }

    @Bean
    public Binding shippingBinding(Queue shippingQueue, DirectExchange shippingExchange) {
        return BindingBuilder.bind(shippingQueue).to(shippingExchange).with(SHIPPING_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
