package com.globalbooks.payments_service.listener;

import com.globalbooks.payments_service.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @RabbitListener(queues = "orderQueue")
    public void receiveOrder(Order order) {
        System.out.println("Received order for payment: " + order.getId());
        System.out.println("Processing payment for order: " + order.getId());
        System.out.println("Payment successful for order: " + order.getId());
    }
}