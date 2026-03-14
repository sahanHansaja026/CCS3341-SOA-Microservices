package com.globalbooks.shipping_service.messaging;

import com.globalbooks.shipping_service.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShippingConsumer {

    @RabbitListener(queues = "orderQueue")
    public void receiveOrder(Order order) {
        System.out.println("Received order for shipping: " + order.getId());
        System.out.println("Processing shipping...");
        // Simulate shipping delay
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        System.out.println("Order shipped successfully: " + order.getId());
    }
}