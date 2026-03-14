package com.globalbooks.payments_service.listener;

import com.globalbooks.payments_service.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @RabbitListener(queues = "${order.queue.name}")
    public void receiveOrder(Order order) {

        System.out.println("Received Order in Payment Service:");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Book: " + order.getBookTitle());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total: " + order.getTotal());

        System.out.println("Processing Payment...");
    }
}
