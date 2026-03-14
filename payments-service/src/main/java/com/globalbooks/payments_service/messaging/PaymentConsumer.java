package com.globalbooks.payments_service.messaging;

import com.globalbooks.payments_service.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @RabbitListener(queues = "orderQueue")
    public void processPayment(Order order) {

        System.out.println("Received order for payment: " + order.getId());

        System.out.println("Processing payment...");

        System.out.println("Payment successful for order: " + order.getId());
    }
}