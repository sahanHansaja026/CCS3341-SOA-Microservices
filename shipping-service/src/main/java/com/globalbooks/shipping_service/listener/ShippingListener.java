package com.globalbooks.shipping_service.listener;

import com.globalbooks.shipping_service.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShippingListener {

    @RabbitListener(queues = RabbitConfig.SHIPPING_QUEUE)
    public void receiveOrder(OrderMessage order) {
        System.out.println("Received order for shipment: " + order.getOrderId());
        System.out.println("Shipping to: " + order.getCustomerName() + ", " + order.getAddress());
        // Add shipment processing logic here
    }

    // Simple inner class to represent incoming order
    public static class OrderMessage {
        private String orderId;
        private String customerName;
        private String address;

        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }

        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }
}
